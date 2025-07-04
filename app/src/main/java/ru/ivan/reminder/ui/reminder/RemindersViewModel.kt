package ru.ivan.reminder.ui.reminder

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.ivan.reminder.data.repository.ReminderRepository
import ru.ivan.reminder.domain.Reminder
import ru.ivan.reminder.utils.DateUtils.formatDDMMYYYY
import ru.ivan.reminder.utils.DateUtils.parseDDMMYYYYDate
import java.util.Calendar

class RemindersViewModel(
    private val reminderRepository: ReminderRepository,
) : ViewModel(), ContainerHost<RemindersUiState, Nothing> {
    override val container: Container<RemindersUiState, Nothing> =
        container(RemindersUiState())

    init {
        loadData()
    }

    private fun loadData() = intent {
        try {
            reduce {
                state.copy(
                    skeleton = state.data == null,
                    refresh = state.data != null,
                    error = null
                )
            }
            val reminders = reminderRepository.getAllReminder()
            reduce {
                state.copy(
                    skeleton = false,
                    refresh = false,
                    data = reminders
                )
            }
        } catch (e: Exception) {
            reduce {
                state.copy(
                    refresh = false,
                    error = e.message
                )
            }
        }
    }

    fun changeReminder(reminder: String) = intent {
        reduce {
            state.copy(
                reminder = reminder
            )
        }
    }

    fun showDatePicker() = intent {
        reduce {
            val currentDateMillisecond = state.date?.parseDDMMYYYYDate()?.time
            val calendar = Calendar.getInstance()
            val newCalendar = if (currentDateMillisecond != null) {
                calendar.timeInMillis = currentDateMillisecond
                calendar
            } else {
                calendar.set(Calendar.HOUR_OF_DAY, 0)
                calendar.set(Calendar.MINUTE, 0)
                calendar.set(Calendar.SECOND, 0)
                calendar.set(Calendar.MILLISECOND, 0)
                calendar
            }
            newCalendar.add(Calendar.DAY_OF_MONTH, 1)

            state.copy(
                dialogs = state.dialogs + RemindersDialogs.ShowDialogDate(
                    generateCalendarForMillisecond(newCalendar)
                )
            )
        }
    }

    fun showTimePicker() = intent {
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)
        val is24hour = calendar.get(Calendar.AM_PM) == Calendar.AM
        val isAfternoon = calendar.get(Calendar.AM_PM) == Calendar.PM

        reduce {
            state.copy(
                dialogs = state.dialogs + RemindersDialogs.ShowDialogTime(
                    hour = hour,
                    minute = minute,
                    is24hour = is24hour,
                    isAfternoon = isAfternoon
                )
            )
        }
    }

    private fun generateCalendarForMillisecond(calendar: Calendar): Long {
        return calendar.timeInMillis
    }

    fun setNewDate(date: Long?) = intent {
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = date ?: 0

        reduce {
            state.copy(
                date = calendar.time.formatDDMMYYYY()
            )
        }
    }

    fun setNewTime(hour: Int, minute: Int, is24hour: Boolean, isAfternoon: Boolean) = intent {
        reduce {
            state.copy(
                time = RemindersUiState.TimeData(
                    hour = hour,
                    minute = minute,
                    is24hour = is24hour,
                    isAfternoon = isAfternoon
                )
            )
        }
    }

    fun updateTime(time: String) = intent {
        reduce {
            state.copy(
                timeString = time
            )
        }
    }

    fun closeDialog(dialog: RemindersDialogs) = intent {
        reduce {
            state.copy(
                dialogs = state.dialogs - dialog
            )
        }
    }

    fun addReminder() = intent {
        reminderRepository.addReminder(
            Reminder(
                text = state.reminder.orEmpty(),
                date = state.date.orEmpty(),
                time = state.timeString.orEmpty()
            )
        )
        loadData()
    }

    fun removeReminder(id: Int) = intent {
        reminderRepository.removeReminder(id)
        loadData()
    }
}