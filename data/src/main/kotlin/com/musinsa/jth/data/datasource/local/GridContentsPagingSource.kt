package com.musinsa.jth.data.datasource.local

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.musinsa.jth.data.model.local.ContentsType
import com.musinsa.jth.domain.model.remote.ContentsItem
import java.lang.Exception

class GridContentsPagingSource(private val localSource: ContentsLocalSourceImpl) :
    PagingSource<Int, ContentsItem>() {
    override fun getRefreshKey(state: PagingState<Int, ContentsItem>): Int? {
        return state.anchorPosition?.let { position ->
            state.closestPageToPosition(position)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(position)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, ContentsItem> {
        val page = params.key ?: 1
        val list = localSource.getContentsByPageNumber(ContentsType.GRID.name, page)

        return try {
            LoadResult.Page(
                data = list,
                prevKey = if (page == 1) {
                    null
                } else {
                    page - 1
                },
                nextKey = if (list.isEmpty()) {
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