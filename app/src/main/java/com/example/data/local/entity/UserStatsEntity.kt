package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_stats")
data class UserStatsEntity(
    @PrimaryKey
    val id: Int = 1,
    val totalScore: Int = 0,
    val totalGamesPlayed: Int = 0,
    val totalCorrectAnswers: Int = 0,
    val totalWrongAnswers: Int = 0,
    val stagesCompletedCount: Int = 0,
    val highestStreak: Int = 0,
    val soundEnabled: Boolean = true,
    val vibrationEnabled: Boolean = true
)
