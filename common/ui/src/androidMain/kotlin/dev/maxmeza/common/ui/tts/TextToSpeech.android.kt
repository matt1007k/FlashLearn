package dev.maxmeza.common.ui.tts

import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import java.util.Locale

private const val TAG = "FlashLearnTTS"

private var tts: TextToSpeech? = null
private var isInitialized = false
private var initSuccess = false
private var pendingText: String? = null
private var pendingLang: String? = null

fun initTts(context: Context) {
    if (tts != null) return
    Log.d(TAG, "Initializing TTS...")
    tts = TextToSpeech(context.applicationContext) { status ->
        if (status == TextToSpeech.SUCCESS) {
            Log.d(TAG, "TTS engine initialized successfully")
            isInitialized = true
            initSuccess = true

            val result = tts?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.w(TAG, "English locale not fully supported, using default")
            }

            pendingText?.let { text ->
                pendingLang?.let { lang ->
                    Log.d(TAG, "Speaking pending text: $text ($lang)")
                    doSpeak(text, lang)
                }
                pendingText = null
                pendingLang = null
            }
        } else {
            Log.e(TAG, "TTS initialization failed with status: $status")
            isInitialized = true
            initSuccess = false
        }
    }
}

private fun getLocaleForLanguage(languageCode: String): Locale {
    return when (languageCode.lowercase().trim()) {
        "en" -> Locale.US
        "es" -> Locale("es", "ES")
        "fr" -> Locale.FRANCE
        "de" -> Locale.GERMANY
        "it" -> Locale.ITALY
        "pt" -> Locale("pt", "BR")
        "ja" -> Locale.JAPAN
        "ko" -> Locale.KOREA
        "zh" -> Locale.CHINA
        "ru" -> Locale("ru", "RU")
        "ar" -> Locale("ar", "SA")
        "hi" -> Locale("hi", "IN")
        "nl" -> Locale("nl", "NL")
        "sv" -> Locale("sv", "SE")
        "pl" -> Locale("pl", "PL")
        "tr" -> Locale("tr", "TR")
        "th" -> Locale("th", "TH")
        "cs" -> Locale("cs", "CZ")
        "da" -> Locale("da", "DK")
        "fi" -> Locale("fi", "FI")
        "el" -> Locale("el", "GR")
        "he" -> Locale("he", "IL")
        "hu" -> Locale("hu", "HU")
        "id" -> Locale("id", "ID")
        "ms" -> Locale("ms", "MY")
        "no" -> Locale("no", "NO")
        "ro" -> Locale("ro", "RO")
        "sk" -> Locale("sk", "SK")
        "uk" -> Locale("uk", "UA")
        "vi" -> Locale("vi", "VN")
        else -> {
            Log.w(TAG, "Unknown language code: $languageCode, falling back to US English")
            Locale.US
        }
    }
}

private fun doSpeak(text: String, languageCode: String) {
    val engine = tts ?: run {
        Log.e(TAG, "TTS engine is null")
        return
    }

    val locale = getLocaleForLanguage(languageCode)
    Log.d(TAG, "Setting language to: ${locale.displayName} (${locale.language})")

    val langResult = engine.isLanguageAvailable(locale)
    Log.d(TAG, "Language availability check result: $langResult")

    when {
        langResult >= TextToSpeech.LANG_AVAILABLE -> {
            engine.language = locale
        }
        langResult == TextToSpeech.LANG_COUNTRY_AVAILABLE -> {
            engine.language = locale
        }
        langResult == TextToSpeech.LANG_COUNTRY_VAR_AVAILABLE -> {
            engine.language = locale
        }
        else -> {
            Log.w(TAG, "Language ${locale.displayName} not available, trying anyway")
            engine.language = locale
        }
    }

    val params = android.os.Bundle()
    val uttId = "utt_${System.currentTimeMillis()}"
    val speakResult = engine.speak(text, TextToSpeech.QUEUE_FLUSH, params, uttId)
    Log.d(TAG, "speak() returned: $speakResult for text: '$text'")
}

actual fun speakText(text: String, languageCode: String) {
    Log.d(TAG, "speakText called: text='$text', lang='$languageCode', initialized=$isInitialized, success=$initSuccess")

    if (!isInitialized) {
        Log.d(TAG, "TTS not initialized yet, queuing as pending")
        pendingText = text
        pendingLang = languageCode
        return
    }

    if (!initSuccess) {
        Log.e(TAG, "TTS engine failed to initialize, cannot speak")
        return
    }

    doSpeak(text, languageCode)
}

actual fun stopTts() {
    tts?.stop()
}
