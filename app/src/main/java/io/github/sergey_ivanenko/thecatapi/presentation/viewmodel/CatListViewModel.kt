package io.github.sergey_ivanenko.thecatapi.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import io.github.sergey_ivanenko.thecatapi.CatApiApp
import io.github.sergey_ivanenko.thecatapi.data.remote.cat.CatApi
import io.github.sergey_ivanenko.thecatapi.data.remote.cat.CatItemRemotePagingSource
import io.github.sergey_ivanenko.thecatapi.domain.CatItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.shareIn

class CatListViewModel : ViewModel() {

    private val catApi: CatApi = CatApiApp.configureRetrofit()
    private var catList = mutableListOf<CatItem>()
    private var _catItems = MutableLiveData<List<CatItem>>()
    var catItem: CatItem? = null

    val catItems = getListData().shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)

    private fun getListData(): Flow<PagingData<CatItem>> {
        return Pager(
            config = PagingConfig(PAGE_SIZE),
            pagingSourceFactory = { CatItemRemotePagingSource(catApi) }
        ).flow.cachedIn(viewModelScope)
    }

    // Not used yet
    /*fun getCatImageById(imageId: String): CatItem? {
        viewModelScope.launch {
            catItems.collect { pagingData -> pagingData.map { catList.add(CatItem(it.id, it.url)) }}
        }

        Log.d("TAG", catList.size.toString())
        Log.d("TAG", catItems.replayCache.size.toString())
        *//*viewModelScope.launch {
            catList = catItems.toCollection(MutableList<CatItem>)
        }*//*
        _catItems = catItems.cachedIn(viewModelScope).asLiveData() as MutableLiveData<List<CatItem>>

        return catItem
    }*/

    companion object {
        private const val PAGE_SIZE = 15
    }
}
