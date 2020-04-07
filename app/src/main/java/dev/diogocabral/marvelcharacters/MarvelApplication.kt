package dev.diogocabral.marvelcharacters

import android.app.Application
import dev.diogocabral.marvelcharacters.di.marvelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class MarvelApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@MarvelApplication)
            modules(marvelModule)
        }
    }
}