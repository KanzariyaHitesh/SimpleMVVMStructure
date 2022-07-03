package com.mvvm.simple

import android.app.Application
import android.content.Context
import com.mvvm.simple.repository.RepoModel
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.dsl.module

open class MyApplication : Application() {

    var _appContext: Context? = null

    override fun onCreate() {
        super.onCreate()
        _appContext = this

        val myModules = module {
            single { RepoModel(this@MyApplication) }
        }

        startKoin {
            androidLogger()
            modules(myModules)
        }
    }

}