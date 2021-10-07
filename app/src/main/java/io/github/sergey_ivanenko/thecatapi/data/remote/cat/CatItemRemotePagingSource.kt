package io.github.sergey_ivanenko.thecatapi.data.remote.cat

import androidx.paging.PagingSource
import androidx.paging.PagingState
import io.github.sergey_ivanenko.thecatapi.domain.CatItem

class CatItemRemotePagingSource(private val apiService: CatApi) : PagingSource<Int, CatItem>() {
    override fun getRefreshKey(state: PagingState<Int, CatItem>): Int? {
        return state.anchorPosition
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CatItem> {
        return try {
            val nextPage: Int = params.key ?: FIRST_PAGE_INDEX
            val response =
                apiService.getCatList(nextPage).map { item -> CatItem(item.id, item.url) }

            LoadResult.Page(
                data = response,
                prevKey = null/*nextPage - 1*/,
                nextKey = nextPage + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    companion object {
        private const val FIRST_PAGE_INDEX = 0
    }
}
