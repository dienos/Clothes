package com.musinsa.jth.presentation.views.main

import androidx.activity.viewModels
import com.musinsa.jth.data.repository.local.ContentsFooterType
import com.musinsa.jth.presentation.R
import com.musinsa.jth.presentation.databinding.MainActivityBinding
import com.musinsa.jth.presentation.viewmodels.MainViewModel
import com.musinsa.jth.presentation.views.base.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {
    private val _viewModel: MainViewModel by viewModels()
    val viewModel: MainViewModel
        get() = _viewModel

    override fun getLayoutResId(): Int = R.layout.main_activity

    override fun initializeViewModel() {
        binding?.viewModel = viewModel
        binding?.activity = this
        binding?.viewModel?.getContents()
    }

    override fun initializeUiEvent() {}

    fun onclickFooter(contentsType :String,  footerType : String) {
        when(footerType) {
            ContentsFooterType.MORE.name -> {
                binding?.viewModel?.getNextContents(contentsType)
            }
        }
    }
}