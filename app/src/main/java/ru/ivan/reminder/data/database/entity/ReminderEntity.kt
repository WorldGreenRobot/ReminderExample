package ru.ivan.reminder.data.database.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("reminder")
data class ReminderEntity(
    @PrimaryKey
    @ColumnInfo("id")
    val id: Int = 0,
    @ColumnInfo("text")
    val text: String,
    @ColumnInfo("date")
    val date: String,
    @ColumnInfo("time")
    val time: String
)
