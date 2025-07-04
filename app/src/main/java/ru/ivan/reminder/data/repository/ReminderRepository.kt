package ru.ivan.reminder.data.repository

import ru.ivan.reminder.domain.Reminder

interface ReminderRepository {
    suspend fun addReminder(reminder: Reminder)
    suspend fun getAllReminder(): List<Reminder>

    suspend fun removeReminder(id: Int)
}