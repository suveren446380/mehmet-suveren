package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.data.local.dao.QuizDao
import com.example.data.local.entity.StageProgressEntity
import com.example.data.local.entity.UserStatsEntity

@Database(
    entities = [StageProgressEntity::class, UserStatsEntity::class],
    version = 1,
    exportSchema = false
)
abstract class QuizDatabase : RoomDatabase() {
    abstract fun quizDao(): QuizDao

    companion object {
        @Volatile
        private var INSTANCE: QuizDatabase? = null

        fun getInstance(context: Context): QuizDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    QuizDatabase::class.java,
                    "bilgi_yarisi_db"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}
