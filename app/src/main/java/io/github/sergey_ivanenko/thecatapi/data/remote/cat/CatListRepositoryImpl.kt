package io.github.sergey_ivanenko.thecatapi.data.remote.cat

import io.github.sergey_ivanenko.thecatapi.CatApiApp
import io.github.sergey_ivanenko.thecatapi.domain.CatItem
import io.github.sergey_ivanenko.thecatapi.domain.CatListRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object CatListRepositoryImpl : CatListRepository {

    override suspend fun getCatList(page: Int): List<CatItem> {
        return withContext(Dispatchers.IO) {
            CatApiApp.configureRetrofit().getCatList(page).map { catApi ->
                CatItem(catApi.id, catApi.url)
            }
        }
    }

    override suspend fun getCatImageById(imageId: String): CatItem {
        return withContext(Dispatchers.IO) {
            val catApiDataItem = CatApiApp.configureRetrofit().getCatImageById(imageId)
            val cat = CatItem.CatMapper.from(catApiDataItem)
            cat
        }
    }
}
