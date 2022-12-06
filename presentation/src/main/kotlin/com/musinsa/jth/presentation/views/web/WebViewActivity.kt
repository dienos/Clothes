package com.musinsa.jth.presentation.views.web

import android.annotation.SuppressLint
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import com.musinsa.jth.presentation.R
import com.musinsa.jth.presentation.databinding.WebActivityBinding
import com.musinsa.jth.presentation.views.base.BaseActivity
import com.musinsa.jth.presentation.views.const.WebConst.WEB_URL
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WebViewActivity : BaseActivity<WebActivityBinding>() {
    override fun getLayoutResId(): Int = R.layout.web_activity

    override fun initializeViewModel() {}

    @SuppressLint("SetJavaScriptEnabled")
    override fun initializeUiEvent() {
        val url = intent.getStringExtra(WEB_URL)

        binding?.web?.settings?.javaScriptEnabled = true

        binding?.web?.webViewClient   = WebViewClient()
        binding?.web?.webChromeClient = WebChromeClient()

        url?.let {
            binding?.web?.loadUrl(it)
        }
    }

    override fun onBackPressed() {
        if (binding?.web?.canGoBack()!!) {
            binding?.web?.goBack()
        } else {
            super.onBackPressed()
        }
    }
}

