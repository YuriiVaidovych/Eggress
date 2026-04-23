package com.yuriivaidovych.eggressapp.data

import android.content.Context
import androidx.room.*
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [User::class, Habit::class, HabitLog::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context, scope: CoroutineScope): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "eggress_database"
                )
                    .addCallback(AppDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class AppDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch(Dispatchers.IO) {
                    val dao = database.appDao()
                    val colors = listOf("#FF5733", "#33FF57", "#3357FF", "#F333FF")

                    // Створюємо по 15 записів у кожну таблицю
                    for (i in 1..15) {
                        val uId = dao.insertUser(User(login = "User_$i", email = "u$i@mail.com", password_hash = "p$i"))

                        val hId = dao.insertHabit(Habit(
                            user_id = uId.toInt(),
                            title = "Test Habit $i",
                            description = "Desc $i",
                            period_n = 1,
                            current_points = (0..60).random(),
                            egg_color_hex = colors.random()
                        ))

                        dao.insertHabitLog(HabitLog(habit_id = hId.toInt(), points_added = (1..5).random()))
                    }
                }
            }
        }
    }
}