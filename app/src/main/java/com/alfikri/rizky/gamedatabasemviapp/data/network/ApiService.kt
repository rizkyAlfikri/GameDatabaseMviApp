package com.alfikri.rizky.gamedatabasemviapp.data.network

import com.alfikri.rizky.gamedatabasemviapp.data.model.GameResponseModel
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version ApiService, v 0.1 11/8/2021 7:31 PM by Rizky Alfikri Rachmat
 */
interface ApiService {

    @GET("games")
    suspend fun searchGames(
        @Query("key") key: String,
        @Query("search") queryGame: String
    ): GameResponseModel
}