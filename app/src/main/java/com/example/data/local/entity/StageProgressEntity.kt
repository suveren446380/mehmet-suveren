package com.example.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stage_progress")
data class StageProgressEntity(
    @PrimaryKey
    val stageNumber: Int,
    val isUnlocked: Boolean = false,
    val isCompleted: Boolean = false,
    val starsEarned: Int = 0, // 0..3
    val bestScore: Int = 0,
    val bestCorrectCount: Int = 0,
    val bestTimeSeconds: Int = 0,
    val lastCompletedAt: Long = 0L
)
