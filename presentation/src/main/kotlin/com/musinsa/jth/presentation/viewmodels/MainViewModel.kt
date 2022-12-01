package com.musinsa.jth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.musinsa.jth.domain.model.remote.Banner
import dagger.hilt.android.lifecycle.HiltViewModel
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.usecase.GetBannersUseCase
import com.musinsa.jth.domain.usecase.GetContentsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getContentsUseCase: GetContentsUseCase,
    private val getBannersUseCase: GetBannersUseCase,
) : BaseViewModel() {
    private var _contentsData = MutableLiveData<Data>()
    val contentsData: LiveData<Data> = _contentsData

    private var _bannersData = MutableLiveData<List<Banner>>()
    val bannersData: LiveData<List<Banner>> = _bannersData

    fun getContents() {
        getContentsUseCase(scope = viewModelScope, { result ->
            _contentsData.value = result
            _bannersData.value = getBannersUseCase(result)

            updateProgress(false)
        }, {
            updateToast(it)
            updateProgress(false)
        })
    }
}