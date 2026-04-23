package com.yuriivaidovych.eggressapp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.yuriivaidovych.eggressapp.data.AppDatabase
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val database = AppDatabase.getDatabase(this, lifecycleScope)

        lifecycleScope.launch {
            try {
                val dao = database.appDao()

                if (dao.getUsersCount() == 0) {
                    delay(1000)
                }

                val users = dao.getAllUsers()
                val habits = dao.getAllHabits()
                val logs = dao.getAllLogs()

                Log.d("MY_DB", "========================================")
                Log.d("MY_DB", "DETAILED DATABASE EXPORT")

                Log.d("MY_DB", "--- TABLE: USERS ---")
                users.forEach { Log.d("MY_DB", "User: ID=${it.user_id}, Login=${it.login}, Email=${it.email}") }

                Log.d("MY_DB", "--- TABLE: HABITS ---")
                habits.forEach { Log.d("MY_DB", "Habit: ID=${it.habit_id}, UserID=${it.user_id}, Title=${it.title}, Points=${it.current_points}") }

                Log.d("MY_DB", "--- TABLE: HABIT_LOGS ---")
                logs.forEach { Log.d("MY_DB", "Log: ID=${it.log_id}, HabitID=${it.habit_id}, Added=${it.points_added}") }

                Log.d("MY_DB", "========================================")
                Log.d("MY_DB", "SUMMARY REPORT:")
                Log.d("MY_DB", "Total Users: ${users.size}")
                Log.d("MY_DB", "Total Habits: ${habits.size}")
                Log.d("MY_DB", "Total Logs: ${logs.size}")
                Log.d("MY_DB", "========================================")

                if (users.size >= 10) {
                    Log.d("MY_DB", "RESULT: Final success state reached.")
                }

            } catch (e: Exception) {
                Log.e("MY_DB", "Error during data export: ${e.message}")
            }
        }
    }
}