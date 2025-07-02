package ru.ivan.reminder

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import ru.ivan.reminder.di.dataModule
import ru.ivan.reminder.di.presenterModule

class ReminderApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@ReminderApplication)
            modules(dataModule, presenterModule)
        }
    }
}