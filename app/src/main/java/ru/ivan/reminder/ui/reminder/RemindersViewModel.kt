package ru.ivan.reminder.ui.reminder

import androidx.lifecycle.ViewModel
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import ru.ivan.reminder.data.repository.ReminderRepository
import ru.ivan.reminder.utils.DateUtils.parseDDMMYYYYDate

class RemindersViewModel(
    private val reminderRepository: ReminderRepository,
): ViewModel(), ContainerHost<RemindersUiState, RemindersSideEffect> {
    override val container: Container<RemindersUiState, RemindersSideEffect> = container(RemindersUiState())

    init {
        loadData()
    }

    private fun loadData() = intent{
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
                    refresh =  false,
                    data = reminders
                )
            }
        }catch (e: Exception) {
            reduce {
                state.copy(
                    refresh = false,
                    error = e.message
                )
            }
        }
    }

    fun changeReminder(reminder: String) = intent{
        reduce {
            state.copy(
                reminder = reminder
            )
        }
    }

    fun showDatePicker(date: String) = intent{
        postSideEffect(
            RemindersSideEffect.ShowDialogDate(date.parseDDMMYYYYDate()?.time ?: 0)
        )
    }
}