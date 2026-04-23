package com.yuriivaidovych.eggressapp.data

import androidx.room.*

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true) val user_id: Int = 0,
    val login: String,
    val email: String,
    val password_hash: String,
    val theme_preference: Int = 0,
    val language: String = "uk",
    val avatar_uri: String? = null,
    val created_at: Long = System.currentTimeMillis()
)

@Entity(
    tableName = "habits",
    foreignKeys = [ForeignKey(entity = User::class, parentColumns = ["user_id"], childColumns = ["user_id"], onDelete = ForeignKey.CASCADE)]
)
data class Habit(
    @PrimaryKey(autoGenerate = true) val habit_id: Int = 0,
    val user_id: Int,
    val title: String,
    val description: String?,
    val period_n: Int,
    val current_points: Int = 0,
    val egg_color_hex: String,
    val last_check_in: Long? = null,
    val hatched_at: Long? = null
)

@Entity(
    tableName = "habit_logs",
    foreignKeys = [ForeignKey(entity = Habit::class, parentColumns = ["habit_id"], childColumns = ["habit_id"], onDelete = ForeignKey.CASCADE)]
)
data class HabitLog(
    @PrimaryKey(autoGenerate = true) val log_id: Int = 0,
    val habit_id: Int,
    val points_added: Int,
    val log_timestamp: Long = System.currentTimeMillis()
)