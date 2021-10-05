package io.github.sergey_ivanenko.thecatapi.domain

interface CatListRepository {
    suspend fun getCatList(page: Int): List<CatItem>
}
