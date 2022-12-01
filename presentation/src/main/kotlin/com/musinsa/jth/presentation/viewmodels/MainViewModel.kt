package com.musinsa.jth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.usecase.GetContentsUseCase
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getContentsUseCase: GetContentsUseCase,
) : BaseViewModel() {

    private var _contentsData = MutableLiveData<Data>()
    val contentsData: LiveData<Data> = _contentsData

    fun getContents() {
        getContentsUseCase(scope = viewModelScope, { result ->
            _contentsData.value = result
            updateProgress(false)
        }, {
            updateToast(it)
            updateProgress(false)
        })
    }
}