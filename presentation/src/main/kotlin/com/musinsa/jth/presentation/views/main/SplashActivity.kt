package com.musinsa.jth.presentation.views.main

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewTreeObserver
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.musinsa.jth.presentation.MuSinSaApplication.Companion.networkUtil
import com.musinsa.jth.presentation.R
import com.musinsa.jth.presentation.utils.AnimationUtil
import com.musinsa.jth.presentation.viewmodels.SplashViewModel

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity() {
    private val viewModel by viewModels<SplashViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        networkUtil()?.setCurrentContext(this)
        networkUtil()?.registerNetworkCallback()

        setContentView(R.layout.splash_activity)

        if(networkUtil()?.checkNetwork()!!) {
            val handler = Handler(mainLooper)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
                val content: View = findViewById(android.R.id.content)
                content.viewTreeObserver.addOnPreDrawListener(
                    object : ViewTreeObserver.OnPreDrawListener {
                        override fun onPreDraw(): Boolean {
                            return if (viewModel.complete.value) {
                                content.viewTreeObserver.removeOnPreDrawListener(this)
                                startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                                finish()
                                true
                            } else {
                                false
                            }
                        }
                    }
                )

                handler.postDelayed({
                    viewModel.updateComplete()
                }, 500)
            } else {
                val aniUtil = AnimationUtil(this, R.drawable.avd_anim)
                aniUtil.playAnimation(findViewById(R.id.anim)) {
                    startActivity(Intent(this@SplashActivity, MainActivity::class.java))
                    finish()
                }
            }
        } else {
            networkUtil()?.networkNotConnect()
        }
    }
}