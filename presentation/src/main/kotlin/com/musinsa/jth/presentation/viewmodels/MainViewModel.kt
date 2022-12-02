package com.musinsa.jth.presentation.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.musinsa.jth.domain.model.remote.Banner
import com.musinsa.jth.domain.model.remote.ContentsItem
import dagger.hilt.android.lifecycle.HiltViewModel
import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.model.remote.DataItem
import com.musinsa.jth.domain.usecase.ConvertContentsListToMapUseCase
import com.musinsa.jth.domain.usecase.GetContentsByPagingUseCase
import com.musinsa.jth.domain.usecase.GetContentsUseCase
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getContentsUseCase: GetContentsUseCase,
    private val convertContentsListToMapUseCase: ConvertContentsListToMapUseCase,
    private val getContentsByPagingUseCase: GetContentsByPagingUseCase
) : BaseViewModel() {
    private var _contentsData = MutableLiveData<Data>()
    val contentsData: LiveData<Data> = _contentsData

    private var _bannersData = MutableLiveData<List<Banner>>()
    val bannersData: LiveData<List<Banner>> = _bannersData

    private var _contentsMapData = MutableLiveData<Map<String, DataItem>>()
    val contentsMapData: LiveData<Map<String, DataItem>> = _contentsMapData

    fun getContents() {
        getContentsUseCase(scope = viewModelScope, { result ->
            _contentsData.value = result
            _contentsMapData.value = convertContentsListToMapUseCase(result)

            updateProgress(false)
        }, {
            updateToast(it)
            updateProgress(false)
        })
    }

    fun getContentsByType(type : String): Flow<PagingData<ContentsItem>> =
        getContentsByPagingUseCase(type)
}