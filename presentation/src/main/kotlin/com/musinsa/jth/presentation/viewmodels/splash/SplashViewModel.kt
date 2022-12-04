package com.musinsa.jth.presentation.viewmodels.splash

import com.musinsa.jth.presentation.viewmodels.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class SplashViewModel : BaseViewModel() {
    private val _complete = MutableStateFlow(false)
    val complete = _complete.asStateFlow()

    fun updateComplete(){
        _complete.value = true
    }
}