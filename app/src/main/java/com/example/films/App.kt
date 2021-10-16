package com.example.films

import android.app.Application
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.FormatStrategy
import com.orhanobut.logger.Logger
import com.orhanobut.logger.PrettyFormatStrategy
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(appModule)
        }

        val formatStrategy: FormatStrategy = PrettyFormatStrategy
            .newBuilder()
            .showThreadInfo(true)
            .methodOffset(5)
            .tag("")
            .build()

        Logger.addLogAdapter(AndroidLogAdapter(formatStrategy))

        Timber.plant(object : Timber.DebugTree() {

            override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
                Logger.log(priority, "-$tag", message, t)
            }

        })

        Timber.d("Inside App!")
    }
}