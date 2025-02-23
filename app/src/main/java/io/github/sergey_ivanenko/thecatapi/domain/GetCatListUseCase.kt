package io.github.sergey_ivanenko.thecatapi.domain

class GetCatListUseCase(private val catListRepository: CatListRepository) {
    suspend fun getCatList(page: Int): List<CatItem> = catListRepository.getCatList(page)
}
