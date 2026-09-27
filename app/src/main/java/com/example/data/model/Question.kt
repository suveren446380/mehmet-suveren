package com.example.data.model

data class Question(
    val id: Int,
    val stage: Int,
    val category: String,
    val question: String,
    val options: List<String>,
    val correctAnswerIndex: Int,
    val explanation: String,
    val points: Int
)

data class StageInfo(
    val stageNumber: Int,
    val title: String,
    val subtitle: String,
    val difficultyLabel: String,
    val iconName: String,
    val minPassingScore: Int = 7, // 7 out of 10 to unlock next stage
    val totalQuestions: Int = 10,
    val pointPerQuestion: Int = stageNumber * 100
)

enum class LifelineType {
    FIFTY_FIFTY,
    AUDIENCE,
    EXTRA_TIME,
    DOUBLE_DIP
}
