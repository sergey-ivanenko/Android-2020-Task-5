package io.github.sergey_ivanenko.thecatapi.data.remote.cat

import io.github.sergey_ivanenko.thecatapi.domain.CatItem
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Headers

interface CatApi {

    //@Headers("x-api-key: 45cd65e4-aaa9-4e43-be53-4879d3a8e9cf")
    @Headers("Content-Type: application/json")
    @GET("search?limit=10&page=0&order=Acs")
    suspend fun getCatList(): /*Response<>*/List<CatApiDataItem>

    /*@GET("/images/{image_id}")
    suspend fun getImageById()*/
}
