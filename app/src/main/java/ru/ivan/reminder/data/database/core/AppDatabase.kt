package ru.ivan.reminder.data.database.core

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.ivan.reminder.data.database.dao.ReminderDao
import ru.ivan.reminder.data.database.entity.ReminderEntity


@Database(entities = [ReminderEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun reminderDao(): ReminderDao
}