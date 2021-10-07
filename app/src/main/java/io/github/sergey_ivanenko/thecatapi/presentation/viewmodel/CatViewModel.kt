package io.github.sergey_ivanenko.thecatapi.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sergey_ivanenko.thecatapi.data.remote.cat.CatListRepositoryImpl
import io.github.sergey_ivanenko.thecatapi.domain.CatItem
import io.github.sergey_ivanenko.thecatapi.domain.CatListRepository
import io.github.sergey_ivanenko.thecatapi.domain.GetCatImageUseCase
import io.github.sergey_ivanenko.thecatapi.domain.GetCatListUseCase
import kotlinx.coroutines.launch

class CatViewModel(/*private val repository: CatListRepository*/) : ViewModel() {

    private val repository: CatListRepository = CatListRepositoryImpl

    private val getCatListUseCase = GetCatListUseCase(repository)
    private val getCatImageUseCase = GetCatImageUseCase(repository)
    private val _catItems = MutableLiveData<List<CatItem>>()
    val catItems: LiveData<List<CatItem>> get() = _catItems

    init {
        loadMore()
    }

    fun loadMore(page: Int = 0)/*: LiveData<List<CatItem>>*/ {
        /*val catListData = MutableLiveData<List<CatItem>>()*/
        viewModelScope.launch {
            _catItems.value = getCatListUseCase.getCatList(page)
        }

        /*return catListData*/
    }

    /*fun getCatList(page: Int = 0)*//*: LiveData<List<CatItem>>*//* {
        *//*val catListData = MutableLiveData<List<CatItem>>()*//*
        viewModelScope.launch {
            _catItems.value = getCatListUseCase.getCatList(page)
        }

        *//*return catListData*//*
    }*/

    fun getCatImageById(imageId: String): CatItem? {
        Log.d("TAG", _catItems.value?.size.toString())
        return _catItems.value?.find { it.id.equals(imageId) }
        /*val catListData = MutableLiveData<List<CatItem>>()
        val list = mutableListOf<CatItem>()
        *//*var cat: CatItem? = null*//*
        viewModelScope.launch {
            *//*val catItem = getCatImageUseCase.getCatImageById(imageId)*//*
            list.add(getCatImageUseCase.getCatImageById(imageId))
            catListData.value = list
        }

        return catListData.value?.first()*/
    }

    companion object {
        var page: Int = 0
    }

    /*val catList =
        getCatListUseCase.getCatList().shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)*/

    /*class Factory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            TODO("Not yet implemented")
        }
    }*/
}
