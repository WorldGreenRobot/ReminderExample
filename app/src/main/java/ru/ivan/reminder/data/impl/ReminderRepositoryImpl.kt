package ru.ivan.reminder.data.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.ivan.reminder.data.database.dao.ReminderDao
import ru.ivan.reminder.data.mapper.ReminderMapper.mapToDatabase
import ru.ivan.reminder.data.mapper.ReminderMapper.mapToDomain
import ru.ivan.reminder.data.repository.ReminderRepository
import ru.ivan.reminder.domain.Reminder

class ReminderRepositoryImpl(
    private val reminderDao: ReminderDao
) : ReminderRepository {

    override suspend fun insert(reminder: Reminder) {
        withContext(Dispatchers.IO) {
            reminderDao.insert(reminder.mapToDatabase())
        }
    }

    override suspend fun getAllReminder(): List<Reminder> {
        return withContext(Dispatchers.IO){
            reminderDao.getAll().map { it.mapToDomain() }
        }
    }
}