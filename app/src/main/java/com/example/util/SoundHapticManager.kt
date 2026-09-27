package com.example.util

import android.content.Context
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.SoundPool
import android.media.ToneGenerator
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager
import android.speech.tts.TextToSpeech
import com.example.R
import java.util.Locale

class SoundHapticManager(private val context: Context) : TextToSpeech.OnInitListener {

    private var toneGenerator: ToneGenerator? = null
    private var soundPool: SoundPool? = null
    private var textToSpeech: TextToSpeech? = null
    private var isTtsReady: Boolean = false

    private var babaProSoundId: Int = 0
    private var osurukSoundId: Int = 0
    private var applauseSoundId: Int = 0
    private var babalarSoundId: Int = 0
    private var cozmeseydinSoundId: Int = 0
    private var aferinSoundId: Int = 0

    private var isBabaProLoaded: Boolean = false
    private var isOsurukLoaded: Boolean = false
    private var isApplauseLoaded: Boolean = false
    private var isBabalarLoaded: Boolean = false
    private var isCozmeseydinLoaded: Boolean = false
    private var isAferinLoaded: Boolean = false

    private data class VoiceConfig(
        val soundId: Int,
        val isLoaded: Boolean,
        val rawResId: Int,
        val phrase: String
    )

    init {
        try {
            toneGenerator = ToneGenerator(AudioManager.STREAM_MUSIC, 70)
        } catch (_: Exception) {
            toneGenerator = null
        }

        try {
            textToSpeech = TextToSpeech(context.applicationContext, this)
        } catch (e: Exception) {
            e.printStackTrace()
        }

        try {
            val audioAttributes = AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build()

            soundPool = SoundPool.Builder()
                .setMaxStreams(4)
                .setAudioAttributes(audioAttributes)
                .build()

            soundPool?.setOnLoadCompleteListener { _, sampleId, status ->
                if (status == 0) {
                    if (sampleId == babaProSoundId) isBabaProLoaded = true
                    if (sampleId == osurukSoundId) isOsurukLoaded = true
                    if (sampleId == applauseSoundId) isApplauseLoaded = true
                    if (sampleId == babalarSoundId) isBabalarLoaded = true
                    if (sampleId == cozmeseydinSoundId) isCozmeseydinLoaded = true
                    if (sampleId == aferinSoundId) isAferinLoaded = true
                }
            }

            babaProSoundId = soundPool?.load(context, R.raw.baba_pro, 1) ?: 0
            osurukSoundId = soundPool?.load(context, R.raw.osuruk, 1) ?: 0
            applauseSoundId = soundPool?.load(context, R.raw.applause, 1) ?: 0
            babalarSoundId = soundPool?.load(context, R.raw.babalar_sozunu_tutar, 1) ?: 0
            cozmeseydinSoundId = soundPool?.load(context, R.raw.cozmeseydin_gardas, 1) ?: 0
            aferinSoundId = soundPool?.load(context, R.raw.aferin_sana, 1) ?: 0
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            textToSpeech?.let { tts ->
                val trLocale = Locale("tr", "TR")
                val result = tts.setLanguage(trLocale)
                if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                    // Fallback to default or English
                    tts.language = Locale.getDefault()
                }
                tts.setPitch(1.0f)
                tts.setSpeechRate(1.0f)
                isTtsReady = true
            }
        }
    }

    private val vibrator: Vibrator? by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as? VibratorManager
            vibratorManager?.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        }
    }

    fun playClick(soundEnabled: Boolean, vibrationEnabled: Boolean) {
        if (vibrationEnabled) {
            vibrate(30)
        }
        if (soundEnabled) {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_BEEP, 40)
            } catch (_: Exception) {}
        }
    }

    /**
     * Doğru cevap verildiğinde normal ve net "Baba pıro!" seslendirmesi çalar.
     */
    fun playCorrect(soundEnabled: Boolean, vibrationEnabled: Boolean) {
        if (vibrationEnabled) {
            vibrate(70)
        }
        if (soundEnabled) {
            var played = false
            // 1. Öncelikli olarak doğal stüdyo seslendirmesi 'baba_pro.wav'
            try {
                if (isBabaProLoaded && babaProSoundId != 0) {
                    val streamId = soundPool?.play(babaProSoundId, 1.0f, 1.0f, 1, 0, 1.0f) ?: 0
                    if (streamId != 0) {
                        played = true
                    }
                }
            } catch (_: Exception) {}

            if (!played) {
                try {
                    val mp = MediaPlayer.create(context, R.raw.baba_pro)
                    if (mp != null) {
                        mp.setOnCompletionListener { it.release() }
                        mp.start()
                        played = true
                    }
                } catch (_: Exception) {}
            }

            // 2. Yedek: Eğer ses dosyası çalınamadıysa doğal tonda Text-to-Speech
            if (!played && isTtsReady) {
                try {
                    textToSpeech?.speak("Baba pıro!", TextToSpeech.QUEUE_FLUSH, null, "BABA_PRO_VOICE")
                } catch (_: Exception) {}
            }
        }
    }

    /**
     * Yanlış cevap verildiğinde 2 saniyelik osuruk sesi çalar.
     */
    fun playWrong(soundEnabled: Boolean, vibrationEnabled: Boolean) {
        if (vibrationEnabled) {
            vibrate(180)
        }
        if (soundEnabled) {
            try {
                if (isOsurukLoaded && osurukSoundId != 0) {
                    soundPool?.play(osurukSoundId, 1.0f, 1.0f, 1, 0, 1.0f)
                } else {
                    val mp = MediaPlayer.create(context, R.raw.osuruk)
                    mp?.setOnCompletionListener { it.release() }
                    mp?.start()
                }
            } catch (_: Exception) {
                try {
                    toneGenerator?.startTone(ToneGenerator.TONE_PROP_NACK, 300)
                } catch (_: Exception) {}
            }
        }
    }

    /**
     * Etap tamamlandığında doğru sayısına göre seslendirme:
     * - Doğru sayısı 10 ise: "babalar sözünü tutar."
     * - Doğru sayısı <= 5 ise: "çözmeseydin gardaş."
     * - Diğerlerinde (6-9): "aferin sana."
     */
    fun playStageResultSound(correctCount: Int, soundEnabled: Boolean, vibrationEnabled: Boolean) {
        if (vibrationEnabled) {
            when {
                correctCount == 10 -> vibrate(300)
                correctCount <= 5 -> vibrate(160)
                else -> vibrate(220)
            }
        }
        if (soundEnabled) {
            val config = when {
                correctCount == 10 -> VoiceConfig(
                    soundId = babalarSoundId,
                    isLoaded = isBabalarLoaded,
                    rawResId = R.raw.babalar_sozunu_tutar,
                    phrase = "Babalar sözünü tutar."
                )
                correctCount <= 5 -> VoiceConfig(
                    soundId = cozmeseydinSoundId,
                    isLoaded = isCozmeseydinLoaded,
                    rawResId = R.raw.cozmeseydin_gardas,
                    phrase = "Çözmeseydin gardaş."
                )
                else -> VoiceConfig(
                    soundId = aferinSoundId,
                    isLoaded = isAferinLoaded,
                    rawResId = R.raw.aferin_sana,
                    phrase = "Aferin sana."
                )
            }

            var played = false
            // 1. SoundPool ile doğrudan oynatmayı dene
            try {
                if (config.isLoaded && config.soundId != 0) {
                    val streamId = soundPool?.play(config.soundId, 1.0f, 1.0f, 2, 0, 1.0f) ?: 0
                    if (streamId != 0) {
                        played = true
                    }
                }
            } catch (_: Exception) {}

            // 2. MediaPlayer ile oynatmayı dene
            if (!played) {
                try {
                    val mp = MediaPlayer.create(context, config.rawResId)
                    if (mp != null) {
                        mp.setOnCompletionListener { it.release() }
                        mp.start()
                        played = true
                    }
                } catch (_: Exception) {}
            }

            // 3. Yedek olarak TextToSpeech ile söyle
            if (!played && isTtsReady) {
                try {
                    textToSpeech?.speak(config.phrase, TextToSpeech.QUEUE_FLUSH, null, "STAGE_RESULT_VOICE")
                } catch (_: Exception) {}
            }
        }
    }

    /**
     * Geriye uyumluluk için varsayılan etap tamamlama sesi.
     */
    fun playStageComplete(soundEnabled: Boolean, vibrationEnabled: Boolean) {
        playStageResultSound(correctCount = 10, soundEnabled = soundEnabled, vibrationEnabled = vibrationEnabled)
    }

    fun playLifeline(soundEnabled: Boolean, vibrationEnabled: Boolean) {
        if (vibrationEnabled) {
            vibrate(45)
        }
        if (soundEnabled) {
            try {
                toneGenerator?.startTone(ToneGenerator.TONE_PROP_PROMPT, 120)
            } catch (_: Exception) {}
        }
    }

    private fun vibrate(durationMs: Long) {
        try {
            vibrator?.let {
                if (it.hasVibrator()) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        it.vibrate(VibrationEffect.createOneShot(durationMs, VibrationEffect.DEFAULT_AMPLITUDE))
                    } else {
                        @Suppress("DEPRECATION")
                        it.vibrate(durationMs)
                    }
                }
            }
        } catch (_: Exception) {}
    }

    fun release() {
        try {
            textToSpeech?.stop()
            textToSpeech?.shutdown()
            toneGenerator?.release()
            soundPool?.release()
        } catch (_: Exception) {}
    }
}
