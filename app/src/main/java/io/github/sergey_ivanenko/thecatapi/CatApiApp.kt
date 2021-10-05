package io.github.sergey_ivanenko.thecatapi

import android.app.Application
import io.github.sergey_ivanenko.thecatapi.data.remote.cat.CatApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CatApiApp : Application() {

    /*var catApi: CatApi? = null*/

    override fun onCreate() {
        super.onCreate()

        /*configureRetrofit()*/
    }

    companion object {
        /*private */fun configureRetrofit(): CatApi {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder()
                        .addHeader("x-api-key", "45cd65e4-aaa9-4e43-be53-4879d3a8e9cf")
                        .build()

                    return@addInterceptor chain.proceed(request)
                }
                .addInterceptor(httpLoggingInterceptor)
                .build()

            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.thecatapi.com/v1/images/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            /*catApi = retrofit.create(CatApi::class.java)*/

            return retrofit.create(CatApi::class.java)
        }
    }
}
