package com.alfikri.rizky.gamedatabasemviapp.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version RetrofitService, v 0.1 11/8/2021 8:06 PM by Rizky Alfikri Rachmat
 */
object RetrofitService {

    private fun createService(): Retrofit {
        val okHttp = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BASIC))
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .build()

        return Retrofit.Builder().
            baseUrl("https://api.rawg.io/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttp)
            .build()
    }

    val apiService: ApiService = createService().create(ApiService::class.java)
}