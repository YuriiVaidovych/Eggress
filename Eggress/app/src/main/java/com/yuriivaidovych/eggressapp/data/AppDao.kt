package com.yuriivaidovych.eggressapp.data

import androidx.room.*

@Dao
interface AppDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(user: User): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabit(habit: Habit): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabitLog(log: HabitLog)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM habits")
    suspend fun getAllHabits(): List<Habit>

    @Query("SELECT * FROM habit_logs")
    suspend fun getAllLogs(): List<HabitLog>

    @Query("SELECT COUNT(*) FROM users")
    suspend fun getUsersCount(): Int

    @Query("SELECT COUNT(*) FROM habits")
    suspend fun getHabitsCount(): Int

    @Query("SELECT COUNT(*) FROM habit_logs")
    suspend fun getLogsCount(): Int

    @Update suspend fun updateHabit(habit: Habit)

    @Delete suspend fun deleteHabit(habit: Habit)
}