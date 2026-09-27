package com.example.data.local

import android.content.Context
import com.example.data.model.Question
import com.example.data.model.StageInfo
import org.json.JSONArray
import java.io.BufferedReader
import java.io.InputStreamReader

object QuestionsData {

    val stages: List<StageInfo> = listOf(
        StageInfo(
            stageNumber = 1,
            title = "Etap 1: Başlangıç Seviyesi",
            subtitle = "Genel Kültür & Temel Bilgiler (100 Soru Havuzu)",
            difficultyLabel = "Çok Kolay",
            iconName = "School",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 2,
            title = "Etap 2: Kolay Seviye",
            subtitle = "Türkiye Coğrafyası & Kültür Mirası (100 Soru Havuzu)",
            difficultyLabel = "Kolay",
            iconName = "Landscape",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 3,
            title = "Etap 3: Temel Bilim & Doğa",
            subtitle = "Canlılar, Gezegenler & Doğa Kanunları (100 Soru Havuzu)",
            difficultyLabel = "Kolay - Orta",
            iconName = "Science",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 4,
            title = "Etap 4: Dünya Tarihi & Keşifler",
            subtitle = "Uygarlıklar, İcatlar & Tarihi Anlar (100 Soru Havuzu)",
            difficultyLabel = "Orta",
            iconName = "HistoryEdu",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 5,
            title = "Etap 5: Edebiyat & Sanat",
            subtitle = "Başyapıtlar, Yazarlar & Ressamlar (100 Soru Havuzu)",
            difficultyLabel = "Orta - İleri",
            iconName = "Brush",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 6,
            title = "Etap 6: Spor & Olimpiyatlar",
            subtitle = "Dünya Rekorları & Efsane Sporcular (100 Soru Havuzu)",
            difficultyLabel = "İleri",
            iconName = "SportsSoccer",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 7,
            title = "Etap 7: Sinema & Teknoloji",
            subtitle = "Bilgisayar Öncüleri, Uzay & Kült Filmler (100 Soru Havuzu)",
            difficultyLabel = "Zor",
            iconName = "Memory",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 8,
            title = "Etap 8: İleri Bilim & Felsefe",
            subtitle = "Kuantum, Biyokimya & Felsefi Akımlar (100 Soru Havuzu)",
            difficultyLabel = "Çok Zor",
            iconName = "Psychology",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 9,
            title = "Etap 9: Usta Seviye",
            subtitle = "Az Bilinen Coğrafya & Derin Tarih (100 Soru Havuzu)",
            difficultyLabel = "Uzman",
            iconName = "MilitaryTech",
            minPassingScore = 7
        ),
        StageInfo(
            stageNumber = 10,
            title = "Etap 10: Şampiyonlar Etabı",
            subtitle = "Nobel Ödülleri & Zirve Entelektüel Bilgi (100 Soru Havuzu)",
            difficultyLabel = "Deha",
            iconName = "WorkspacePremium",
            minPassingScore = 7
        )
    )

    private var cachedQuestions: List<Question>? = null

    fun init(context: Context) {
        if (cachedQuestions != null && cachedQuestions!!.size >= 1000) return
        cachedQuestions = loadQuestionsFromAssets(context)
    }

    val questions: List<Question>
        get() = cachedQuestions ?: emptyList()

    fun getQuestionsForStage(stageNumber: Int, limit: Int = 10, shuffle: Boolean = true): List<Question> {
        val pool = (cachedQuestions ?: emptyList()).filter { it.stage == stageNumber }
        if (pool.isEmpty()) return emptyList()
        val selected = if (shuffle) pool.shuffled() else pool
        return selected.take(limit)
    }

    fun getAllQuestionsForStage(stageNumber: Int): List<Question> {
        return (cachedQuestions ?: emptyList()).filter { it.stage == stageNumber }
    }

    private fun loadQuestionsFromAssets(context: Context): List<Question> {
        return try {
            val inputStream = context.assets.open("questions_pool.json")
            val reader = BufferedReader(InputStreamReader(inputStream, "UTF-8"))
            val jsonString = reader.use { it.readText() }
            val jsonArray = JSONArray(jsonString)
            val result = mutableListOf<Question>()

            for (i in 0 until jsonArray.length()) {
                val obj = jsonArray.getJSONObject(i)
                val optionsArray = obj.getJSONArray("options")
                val options = mutableListOf<String>()
                for (j in 0 until optionsArray.length()) {
                    options.add(optionsArray.getString(j))
                }

                result.add(
                    Question(
                        id = obj.getInt("id"),
                        stage = obj.getInt("stage"),
                        category = obj.getString("category"),
                        question = obj.getString("question"),
                        options = options,
                        correctAnswerIndex = obj.getInt("correctAnswerIndex"),
                        explanation = obj.getString("explanation"),
                        points = obj.getInt("points")
                    )
                )
            }
            result
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}
