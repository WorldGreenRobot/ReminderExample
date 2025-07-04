package ru.ivan.reminder.ui.reminder

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.util.fastForEach
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import ru.ivan.reminder.domain.Reminder
import ru.ivan.reminder.ui.components.Screen
import ru.ivan.reminder.ui.dialog.DatePickerModalDialog
import ru.ivan.reminder.ui.dialog.TimePickerModelDialog
import ru.ivan.reminder.ui.reminder.view.InputForm
import ru.ivan.reminder.ui.reminder.view.ReminderItem

@Composable
fun RemindersScreen(
    viewModel: RemindersViewModel = koinViewModel(),
) {

    val state by viewModel.collectAsState()

    RemindersContent(
        state = state,
        onAction = {
            actionHandler(it, viewModel)
        }
    )

    Dialogs(
        dialogs = state.dialogs,
        viewModel = viewModel
    )
}

@Composable
private fun RemindersContent(
    state: RemindersUiState,
    onAction: (RemindersAction) -> Unit = {},
) {
    Screen(
        modifier = Modifier.fillMaxSize(),
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {

            InputForm(
                reminderText = state.reminder.orEmpty(),
                date = state.date.orEmpty(),
                time = state.time?.getTimeString().orEmpty(),
                modifier = Modifier.padding(16.dp),
                onValueChangeReminder = {
                    onAction(RemindersAction.ChangeReminder(it))
                },
                onClickDate = {
                    onAction(RemindersAction.DataDialog)
                },
                onClickTime = {
                    onAction(RemindersAction.TimeDialog)
                }
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 16.dp)
            ) {
                items(state.data.orEmpty(), key = { it.id }) {
                    ReminderItem(
                        reminder = it,
                        modifier = Modifier.padding(vertical = 8.dp)
                    )
                }
            }
        }
    }
}

@Composable
private fun Dialogs(dialogs: List<RemindersDialogs>, viewModel: RemindersViewModel) {

    dialogs.fastForEach { dialog ->
        when (dialog) {
            is RemindersDialogs.ShowDialogDate -> {
                DatePickerModalDialog(
                    milliseconds = dialog.milliseconds,
                    onDateSelected = {
                        viewModel.setNewDate(it)
                    },
                    onDismiss = {
                        viewModel.closeDialog(dialog)
                    }
                )
            }

            is RemindersDialogs.ShowDialogTime -> {
                TimePickerModelDialog(
                    hour = dialog.hour,
                    minute = dialog.minute,
                    is24hour = dialog.is24hour,
                    isAfternoon = dialog.isAfternoon,
                    onDismissRequest = {
                        viewModel.closeDialog(dialog)
                    },
                    onConfirm = { hour, minute, is24hour, isAfternoon ->
                        viewModel.setNewTime(hour, minute, is24hour, isAfternoon)
                    }
                )
            }
        }
    }
}

private fun actionHandler(action: RemindersAction, viewModel: RemindersViewModel) {
    when (action) {
        is RemindersAction.ChangeReminder -> {
            viewModel.changeReminder(action.text)
        }

        is RemindersAction.DataDialog -> {
            viewModel.showDatePicker()
        }

        is RemindersAction.TimeDialog -> {
            viewModel.showTimePicker()
        }
    }
}

sealed interface RemindersAction {
    data class ChangeReminder(val text: String) : RemindersAction
    data object DataDialog : RemindersAction
    data object TimeDialog : RemindersAction
}

@Composable
@Preview
private fun RemindersContentPreview() {
    MaterialTheme {
        RemindersContent(
            state = RemindersUiState(
                data = List(5) {
                    Reminder(
                        id = it,
                        text = "Reminder $it",
                        date = "2023-01-01",
                        time = "12:00"
                    )
                }
            )
        )
    }
}