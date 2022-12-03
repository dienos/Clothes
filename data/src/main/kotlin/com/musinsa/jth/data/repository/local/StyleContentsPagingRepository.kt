package com.musinsa.jth.data.repository.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.musinsa.jth.domain.model.remote.ContentsItem
import com.musinsa.jth.domain.repository.remote.ContentsRepository
import java.lang.Exception

class StyleContentsPagingRepository(private val repository: ContentsRepository) :
    PagingSource<Int, ContentsItem>() {
    override fun getRefreshKey(state: PagingState<Int, ContentsItem>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContentsItem> {
        val page = params.key ?: 1

        val result = repository.getContentsByPageNumber(
            repository.getContentsMap(),
            ContentsType.STYLE.name,
            page
        )

        return try {
            LoadResult.Page(
                data = result,
                prevKey = if (page == 1) {
                    null
                } else {
                    page - 1
                },
                nextKey = if (result.isEmpty()) {
                    null
                } else {
                    page + params.loadSize
                }
            )
        } catch (e: Exception) {
            return LoadResult.Error(e)
        }
    }
}