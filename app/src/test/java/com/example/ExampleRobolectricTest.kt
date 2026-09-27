package com.example

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.example.data.local.QuestionsData
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
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
  fun `verify questions pool has 1000 questions across 10 stages`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    QuestionsData.init(context)

    val allQuestions = QuestionsData.questions
    assertEquals("Total question pool must be exactly 1000", 1000, allQuestions.size)

    val stageCount = QuestionsData.stages.size
    assertEquals("Total stages must be exactly 10", 10, stageCount)

    (1..10).forEach { stageNum ->
      val stagePool = QuestionsData.getAllQuestionsForStage(stageNum)
      assertEquals("Stage $stageNum must have 100 questions in pool", 100, stagePool.size)

      val gameSessionQuestions = QuestionsData.getQuestionsForStage(stageNum, limit = 10)
      assertEquals("Game session for Stage $stageNum must pick 10 questions", 10, gameSessionQuestions.size)

      stagePool.forEach { q ->
        assertEquals("Each question must have 4 options", 4, q.options.size)
        assertTrue("Correct answer index must be in 0..3", q.correctAnswerIndex in 0..3)
        assertTrue("Question text must not be blank", q.question.isNotBlank())
        assertTrue("Explanation must not be blank", q.explanation.isNotBlank())
      }
    }
  }

  @Test
  fun `verify audio resources exist including baba_pro and osuruk`() {
    val context = ApplicationProvider.getApplicationContext<Context>()
    val babaProId = R.raw.baba_pro
    val osurukId = R.raw.osuruk
    val applauseId = R.raw.applause
    assertTrue("baba_pro raw resource must exist", babaProId != 0)
    assertTrue("osuruk raw resource must exist", osurukId != 0)
    assertTrue("Applause raw resource must exist", applauseId != 0)

    val babaProStream = context.resources.openRawResource(babaProId)
    assertNotNull(babaProStream)
    babaProStream.close()

    val osurukStream = context.resources.openRawResource(osurukId)
    assertNotNull(osurukStream)
    osurukStream.close()
  }
}
