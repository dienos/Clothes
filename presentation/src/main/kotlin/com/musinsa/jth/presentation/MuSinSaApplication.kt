package com.musinsa.jth.presentation

import android.app.Application
import android.content.Context
import com.musinsa.jth.presentation.utils.NetworkUtil
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MuSinSaApplication : Application() {
    init {
        instance = this
    }

    companion object {
        lateinit var instance: MuSinSaApplication
        fun applicationContext(): Context {
            return instance.applicationContext
        }

        var networkUtil: NetworkUtil? = NetworkUtil()
        fun networkUtil(): NetworkUtil? = networkUtil
    }

    override fun onCreate() {
        super.onCreate()
    }
}