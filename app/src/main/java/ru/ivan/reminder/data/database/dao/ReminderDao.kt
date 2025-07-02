package ru.ivan.reminder.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.ivan.reminder.data.database.entity.ReminderEntity

@Dao
interface ReminderDao {
    @Query("SELECT * FROM reminder")
    suspend fun getAll(): List<ReminderEntity>

    @Query("SELECT * FROM reminder WHERE id = :id")
    suspend fun getById(id: Int): ReminderEntity?

    @Insert
    suspend fun insert(reminder: ReminderEntity)

    @Query("DELETE FROM reminder WHERE id = :id")
    suspend fun delete(id: Int)

}