package com.example.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.data.local.entity.StageProgressEntity
import com.example.data.local.entity.UserStatsEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface QuizDao {

    @Query("SELECT * FROM stage_progress ORDER BY stageNumber ASC")
    fun getAllStageProgress(): Flow<List<StageProgressEntity>>

    @Query("SELECT * FROM stage_progress WHERE stageNumber = :stageNumber LIMIT 1")
    suspend fun getStageProgress(stageNumber: Int): StageProgressEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStageProgressList(stages: List<StageProgressEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateStageProgress(stage: StageProgressEntity)

    @Update
    suspend fun updateStageProgress(stage: StageProgressEntity)

    @Query("SELECT * FROM user_stats WHERE id = 1 LIMIT 1")
    fun getUserStatsFlow(): Flow<UserStatsEntity?>

    @Query("SELECT * FROM user_stats WHERE id = 1 LIMIT 1")
    suspend fun getUserStats(): UserStatsEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdateUserStats(stats: UserStatsEntity)

    @Query("DELETE FROM stage_progress")
    suspend fun clearStageProgress()

    @Query("DELETE FROM user_stats")
    suspend fun clearUserStats()
}
