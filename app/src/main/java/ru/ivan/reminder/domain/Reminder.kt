package ru.ivan.reminder.domain

import androidx.compose.runtime.Immutable

@Immutable
data class Reminder(
    val id: Int = 0,
    val text: String,
    val date: String,
    val time: String
)