package com.musinsa.jth.domain.usecase

import com.musinsa.jth.domain.model.remote.Data
import com.musinsa.jth.domain.repository.remote.ContentsRepository
import kotlinx.coroutines.*
import java.lang.Exception

class GetContentsUseCase(private val repository: ContentsRepository) {
    operator fun invoke(
        scope: CoroutineScope,
        onResult: (Data) -> Unit = {},
        onFail: (String) -> Unit = {}
    ) {
        scope.launch(Dispatchers.Main) {
            try {
                val result = async {
                    repository.getContents()
                }

                onResult(result.await())
            } catch (e: Exception) {
                e.message?.let {
                    onFail(it)
                } ?: onFail("알수 없는 에러 입니다.")
            }
        }
    }
}