package ru.ivan.reminder.di

import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module
import ru.ivan.reminder.ui.reminder.RemindersViewModel

val presenterModule = module {
    viewModelOf(::RemindersViewModel)
}