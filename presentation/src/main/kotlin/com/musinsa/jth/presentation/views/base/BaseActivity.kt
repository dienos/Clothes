package com.musinsa.jth.presentation.views.base

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.musinsa.jth.presentation.MuSinSaApplication.Companion.networkUtil
import com.musinsa.jth.presentation.utils.NetworkUtil

abstract class BaseActivity<T : ViewDataBinding?> : AppCompatActivity() {
    @LayoutRes
    abstract fun getLayoutResId(): Int
    abstract fun initializeViewModel()
    abstract fun initializeUiEvent()

    var binding: T? = null
        private set

    var progressDialog: ProgressDialog ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, getLayoutResId())
        binding?.lifecycleOwner = this
        initializeViewModel()
        initializeUiEvent()
        networkUtil()?.setCurrentContext(this)
    }

    override fun onStop() {
        super.onStop()
        networkUtil()?.terminateNetworkCallback(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
        networkUtil = null
    }
}