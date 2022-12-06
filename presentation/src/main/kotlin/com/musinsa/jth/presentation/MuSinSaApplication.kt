package com.musinsa.jth.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MuSinSaApplication : Application() {
    override fun onCreate() {
        super.onCreate()
    }
}