package com.musinsa.jth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.musinsa.jth.domain.model.remote.ContentsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.domain.usecase.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getContentsUseCase: GetContentsUseCase,
    private val convertContentsListToMapUseCase: ConvertContentsListToMapUseCase,
    private val getFirstContentsItemListMapUseCase: GetFirstContentsItemListMapUseCase,
) : BaseViewModel() {

    private var _originalContentsMapData = MutableLiveData<Map<String, DataItem>>()
    val originalContentsMapData: LiveData<Map<String, DataItem>> = _originalContentsMapData

    private var _currentContentsMapData = MutableLiveData<Map<String, List<ContentsItem>>>()
    val currentContentsMapData: LiveData<Map<String, List<ContentsItem>>> = _currentContentsMapData

    fun getContents() {
        getContentsUseCase(scope = viewModelScope, { result ->
            _originalContentsMapData.value = convertContentsListToMapUseCase(result)
            _currentContentsMapData.value = getFirstContentsItemListMapUseCase(result)

            updateProgress(false)
        }, {
            updateToast(it)
            updateProgress(false)
        })
    }
}