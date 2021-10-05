package io.github.sergey_ivanenko.thecatapi.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.github.sergey_ivanenko.thecatapi.data.remote.cat.CatListRepositoryImpl
import io.github.sergey_ivanenko.thecatapi.domain.CatItem
import io.github.sergey_ivanenko.thecatapi.domain.CatListRepository
import io.github.sergey_ivanenko.thecatapi.domain.GetCatListUseCase
import kotlinx.coroutines.launch

class CatViewModel(/*private val repository: CatListRepository*/) : ViewModel() {

    private val repository: CatListRepository = CatListRepositoryImpl

    private val getCatListUseCase = GetCatListUseCase(repository)
    private val _items = MutableLiveData<List<CatItem>>()
    val items: LiveData<List<CatItem>> get() = _items

    /*init {
        viewModelScope.launch {
            _items.value = getCatListUseCase.getCatList()
        }
    }*/

    fun getCatList(page: Int): LiveData<List<CatItem>> {
        val catListData = MutableLiveData<List<CatItem>>()
        viewModelScope.launch {
            catListData.value = getCatListUseCase.getCatList(page)
        }

        return catListData
    }



    /*val catList =
        getCatListUseCase.getCatList().shareIn(viewModelScope, SharingStarted.Eagerly, replay = 1)*/

    /*class Factory() : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            TODO("Not yet implemented")
        }

    }*/
}
