package ru.ivan.reminder.ui.reminder

import ru.ivan.reminder.domain.Reminder

data class RemindersUiState (
    val skeleton: Boolean = true,
    val refresh: Boolean = false,
    val data: List<Reminder>? = null,
    val reminder: String? = null,
    val date: String? = null,
    val time: String? = null,
    val error: String? = null
)

sealed interface RemindersSideEffect {
    data class ShowDialogDate(private val date: String) : RemindersSideEffect
}