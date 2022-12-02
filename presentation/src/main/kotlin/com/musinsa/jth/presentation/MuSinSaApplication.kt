package com.musinsa.jth.presentation

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MuSinSaApplication : Application() {
    init{
        instance = this
    }

    companion object {
        lateinit var instance: MuSinSaApplication
        fun applicationContext() : Context {
            return instance.applicationContext
        }
    }

    override fun onCreate() {
        super.onCreate()
    }
}