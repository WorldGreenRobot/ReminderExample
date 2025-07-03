package ru.ivan.reminder.ui.reminder

import ru.ivan.reminder.domain.Reminder

data class RemindersUiState (
    val skeleton: Boolean = true,
    val refresh: Boolean = false,
    val data: List<Reminder>? = null,
    val reminder: String? = null,
    val date: String? = null,
    val time: String? = null,
    val error: String? = null,
    val dialogs: List<RemindersDialogs> = emptyList()
)

sealed interface RemindersDialogs{
    data class ShowDialogDate(val milliseconds: Long) : RemindersDialogs
    data class ShowDialogTime(val hours: Int, val minutes: Int) : RemindersDialogs
}