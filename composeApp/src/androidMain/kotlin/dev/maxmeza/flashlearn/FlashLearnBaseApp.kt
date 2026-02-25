package dev.maxmeza.flashlearn

import android.app.Application
import dev.maxmeza.flashlearn.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class FlashLearnBaseApp: Application() {
    override fun onCreate() {
        super.onCreate()

        initKoin {
            androidLogger()
            androidContext(this@FlashLearnBaseApp)
        }
    }
}