package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.QuestionsData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [34])
class ExampleRobolectricTest {

  @Test
  fun `read string from context`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val appName = context.getString(R.string.app_name)
    assertEquals("Bilgi Yarışı", appName)
  }

  @Test
  fun `verify questions dataset has 100 questions across 10 stages`() {
    val allQuestions = QuestionsData.questions
    assertEquals("Total questions must be exactly 100", 100, allQuestions.size)

    val stageCount = QuestionsData.stages.size
    assertEquals("Total stages must be exactly 10", 10, stageCount)

    (1..10).forEach { stageNum ->
      val stageQuestions = QuestionsData.getQuestionsForStage(stageNum)
      assertEquals("Stage $stageNum must have 10 questions", 10, stageQuestions.size)
      stageQuestions.forEach { q ->
        assertEquals("Each question must have 4 options", 4, q.options.size)
        assertTrue("Correct answer index must be in 0..3", q.correctAnswerIndex in 0..3)
        assertTrue("Question text must not be blank", q.question.isNotBlank())
        assertTrue("Explanation must not be blank", q.explanation.isNotBlank())
      }
    }
  }
}
