package com.yuriivaidovych.eggressapp.data

import com.yuriivaidovych.eggressapp.domain.HabitRepository
import javax.inject.Inject

class HabitRepositoryImpl @Inject constructor(
    private val dao: AppDao
) : HabitRepository {

    override suspend fun insertHabit(habit: Habit) {
        dao.insertHabit(habit)
    }

    override suspend fun updateHabit(habit: Habit) {
        dao.updateHabit(habit)
    }

    override suspend fun deleteHabit(habit: Habit) {
        dao.deleteHabit(habit)
    }

    override suspend fun getAllHabits(): List<Habit> {
        return dao.getAllHabits()
    }
}