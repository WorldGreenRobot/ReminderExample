package ru.ivan.reminder.ui.reminder

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import ru.ivan.reminder.domain.Reminder

data class RemindersUiState(
    val skeleton: Boolean = true,
    val refresh: Boolean = false,
    val data: List<Reminder>? = null,
    val reminder: String? = null,
    val date: String? = null,
    val time: TimeData? = null,
    val timeString: String? = null,
    val error: String? = null,
    val dialogs: List<RemindersDialogs> = emptyList()
) {
    data class TimeData(
        val hour: Int,
        val minute: Int,
        val is24hour: Boolean,
        val isAfternoon: Boolean
    )
}

@Composable
@ReadOnlyComposable
fun RemindersUiState.TimeData.getTimeString(): String {
    return if (is24hour) {
        "${if (hour < 10) "0" else ""}$hour:${if (minute < 10) "0" else ""}$minute"
    } else {
        "${if (hour > 12) hour - 12 else hour}:${if (minute < 10) "0" else ""}$minute ${if (isAfternoon) "PM" else "AM"}"
    }
}

sealed interface RemindersDialogs {
    data class ShowDialogDate(val milliseconds: Long) : RemindersDialogs
    data class ShowDialogTime(
        val hour: Int,
        val minute: Int,
        val is24hour: Boolean,
        val isAfternoon: Boolean
    ) : RemindersDialogs
}