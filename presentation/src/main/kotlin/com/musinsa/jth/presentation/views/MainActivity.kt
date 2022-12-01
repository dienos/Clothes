package com.musinsa.jth.presentation.views

import androidx.activity.viewModels
import com.musinsa.jth.presentation.R
import com.musinsa.jth.presentation.databinding.MainActivityBinding
import com.musinsa.jth.presentation.viewmodels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : BaseActivity<MainActivityBinding>() {
    private val _viewModel: MainViewModel by viewModels()
    private val viewModel: MainViewModel
        get() = _viewModel

    override fun getLayoutResId(): Int = R.layout.main_activity

    override fun initializeViewModel() {
        binding?.viewModel = viewModel
        binding?.viewModel?.getContents()
    }

    override fun initializeUiEvent() {}
}