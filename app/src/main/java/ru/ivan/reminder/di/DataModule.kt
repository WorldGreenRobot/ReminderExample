package ru.ivan.reminder.di

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module
import ru.ivan.reminder.data.database.core.AppDatabase
import ru.ivan.reminder.data.impl.ReminderRepositoryImpl
import ru.ivan.reminder.data.repository.ReminderRepository

val dataModule = module {

    factory {
        Room.databaseBuilder(
            context = get<Context>(),
            klass = AppDatabase::class.java,
            name = "app_database"
        ).build()
    }

    factory {
        get<AppDatabase>().reminderDao()
    }

    factory<ReminderRepository> {
        ReminderRepositoryImpl(get())
    }
}