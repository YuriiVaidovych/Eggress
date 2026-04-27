package com.yuriivaidovych.eggressapp.domain

import com.yuriivaidovych.eggressapp.data.Habit

interface HabitRepository {
    suspend fun insertHabit(habit: Habit)
    suspend fun updateHabit(habit: Habit)
    suspend fun deleteHabit(habit: Habit)
    suspend fun getAllHabits(): List<Habit>
}