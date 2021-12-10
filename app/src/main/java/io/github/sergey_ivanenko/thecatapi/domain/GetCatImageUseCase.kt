package io.github.sergey_ivanenko.thecatapi.domain

class GetCatImageUseCase(private val catListRepository: CatListRepository) {
    suspend fun getCatImageById(imageId: String): CatItem = catListRepository.getCatImageById(imageId)
}
