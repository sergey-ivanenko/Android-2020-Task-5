package io.github.sergey_ivanenko.thecatapi.data.remote.cat

import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface CatApi {

    // @Headers("x-api-key: 45cd65e4-aaa9-4e43-be53-4879d3a8e9cf")
    @Headers("Content-Type: application/json")
    @GET("search?limit=10&order=Acs")/*?limit=10&page=0&order=Acs*/
    suspend fun getCatList(@Query("page") page: Int = 0): /*Response<>*/List<CatApiDataItem>

    @Headers("Content-Type: application/json")
    @GET("{image_id}")
    suspend fun getCatImageById(@Path("image_id") imageId: String): CatApiDataItem
}
