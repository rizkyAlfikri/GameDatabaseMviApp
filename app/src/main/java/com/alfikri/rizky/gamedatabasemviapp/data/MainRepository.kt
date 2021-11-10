package com.alfikri.rizky.gamedatabasemviapp.data

import com.alfikri.rizky.gamedatabasemviapp.data.datasource.NetworkRepository
import com.alfikri.rizky.gamedatabasemviapp.data.model.GameInfoModel
import com.alfikri.rizky.gamedatabasemviapp.state.MainState
import kotlinx.coroutines.flow.Flow

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MainRepository, v 0.1 11/8/2021 9:54 PM by Rizky Alfikri Rachmat
 */
class MainRepository (private val networkRepository: NetworkRepository){

    suspend fun searchGames(queryGame: String): Flow<MainState<List<GameInfoModel>>> {
        return  networkRepository.searchGames(queryGame)
    }
}