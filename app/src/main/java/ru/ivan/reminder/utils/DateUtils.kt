package ru.ivan.reminder.utils

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object DateUtils {
    private const val DD_MM_YYYY = "dd.MM.yyyy"
    private const val HH_MM = "HH:mm"
    private const val DD_MM_YYYY_HH_MM = "dd.MM.yyyy HH:mm"

    fun String.parseDDMMYYYYDate(): Date? {
        return try {
            SimpleDateFormat(DD_MM_YYYY, Locale.getDefault()).parse(this)
        } catch (_: Exception) {
            null
        }
    }

    fun String.parseHHMMDate(): Date? {
        return try {
            SimpleDateFormat(HH_MM, Locale.getDefault()).parse(this)
        } catch (_: Exception) {
            null
        }
    }

    fun String.parseDDMMYYYYHHMMDate(): Date? {
        return try {
            SimpleDateFormat(DD_MM_YYYY_HH_MM, Locale.getDefault()).parse(this)
        } catch (_: Exception) {
            null
        }
    }

    fun Date.formatDDMMYYYY(): String {
        return SimpleDateFormat(DD_MM_YYYY, Locale.getDefault()).format(this)
    }

    fun Date.formatHHMM(): String {
        return SimpleDateFormat(HH_MM, Locale.getDefault()).format(this)
    }

    fun Date.formatDDMMYYYYHHMM(): String {
        return SimpleDateFormat(DD_MM_YYYY_HH_MM, Locale.getDefault()).format(this)
    }
}