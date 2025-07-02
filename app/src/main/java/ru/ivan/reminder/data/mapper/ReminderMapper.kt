package ru.ivan.reminder.data.mapper

import ru.ivan.reminder.data.database.entity.ReminderEntity
import ru.ivan.reminder.domain.Reminder

object ReminderMapper {
    fun Reminder.mapToDatabase(): ReminderEntity {
        return ReminderEntity(
            text = text,
            date = date,
            time = time
        )
    }

    fun ReminderEntity.mapToDomain(): Reminder {
        return Reminder(
            id = id,
            text = text,
            date = date,
            time = time
        )
    }
}