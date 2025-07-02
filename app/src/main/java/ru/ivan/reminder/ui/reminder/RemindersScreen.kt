package ru.ivan.reminder.ui.reminder

import android.app.DatePickerDialog
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import org.koin.compose.viewmodel.koinViewModel
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect
import ru.ivan.reminder.domain.Reminder
import ru.ivan.reminder.ui.components.Screen
import ru.ivan.reminder.ui.dialog.DatePickerModal
import ru.ivan.reminder.ui.reminder.view.InputForm
import ru.ivan.reminder.ui.reminder.view.ReminderItem

@Composable
fun RemindersScreen(
    viewModel: RemindersViewModel = koinViewModel(),
) {

    val state by viewModel.collectAsState()

    val showDatePickerDialog = remember { mutableStateOf(false) }

    viewModel.collectSideEffect {
        when (it) {
            is RemindersSideEffect.ShowDialogDate -> {
                showDatePickerDialog.value = true
            }
        }
    }

/*
    if (showDatePickerDialog.value) {
        DatePickerModal(
            onDateSelected = {

            },
            onDismiss = {
                showDatePickerDialog.value = false
            },
        )
    }*/

    RemindersContent(
        state = state,
        onAction = {
            actionHandler(it, viewModel)
        }
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
                time = state.time.orEmpty(),
                modifier = Modifier.padding(16.dp),
                onValueChangeReminder = {
                    onAction(RemindersAction.ChangeReminder(it))
                },
                onClickDate = {
                    onAction(RemindersAction.DataDialog(it))
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

private fun actionHandler(action: RemindersAction, viewModel: RemindersViewModel) {
    when (action) {
        is RemindersAction.ChangeReminder -> {
            viewModel.changeReminder(action.text)
        }

        is RemindersAction.DataDialog -> {
            viewModel.showDatePicker(action.date)
        }
    }
}

sealed interface RemindersAction {
    data class ChangeReminder(val text: String) : RemindersAction
    data class DataDialog(val date: String) : RemindersAction
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