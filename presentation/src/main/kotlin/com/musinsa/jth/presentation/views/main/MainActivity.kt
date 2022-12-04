package com.musinsa.jth.presentation.views.main

import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import com.musinsa.jth.data.model.local.ContentsFooterType
import com.musinsa.jth.presentation.R
import com.musinsa.jth.presentation.databinding.MainActivityBinding
import com.musinsa.jth.presentation.viewmodels.main.MainViewModel
import com.musinsa.jth.presentation.views.base.BaseActivity
import com.musinsa.jth.presentation.views.base.ProgressDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch


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

    override fun initializeUiEvent() {
        progressDialog = ProgressDialog()

        binding?.lifecycleOwner?.lifecycleScope?.launch {
            viewModel.progressFlow.collect{ isShowing ->

                if (isShowing) {
                    progressDialog?.show(supportFragmentManager, "progress")
                } else {
                    progressDialog?.dismiss()
                }
            }
        }

        binding?.lifecycleOwner?.lifecycleScope?.launch {
            viewModel.toastFlow.collect{ msg ->
                if(msg.isNotEmpty()) {
                    Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    fun onclickFooter(contentsType :String,  footerType : String) {
        when(footerType) {
            ContentsFooterType.MORE.name -> {
                binding?.viewModel?.getNextContents(contentsType)
            }

            ContentsFooterType.REFRESH.name -> {
                binding?.viewModel?.getRandomContents(contentsType)
            }
        }
    }

    override fun onBackPressed() {
        if (System.currentTimeMillis() > viewModel.backPressedTime + 2000) {
            viewModel.backPressedTime = System.currentTimeMillis()
            viewModel.updateToast(getString(R.string.finish_msg))
        } else if (System.currentTimeMillis() <= viewModel.backPressedTime + 2000) {
            finish()
        }
    }
}