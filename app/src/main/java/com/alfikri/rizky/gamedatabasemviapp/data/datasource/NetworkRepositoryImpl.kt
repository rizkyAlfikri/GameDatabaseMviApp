package com.alfikri.rizky.gamedatabasemviapp.data.datasource

import com.alfikri.rizky.gamedatabasemviapp.data.model.GameInfoModel
import com.alfikri.rizky.gamedatabasemviapp.data.network.ApiService
import com.alfikri.rizky.gamedatabasemviapp.state.MainState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version NetworkRepositoryImpl, v 0.1 11/8/2021 8:05 PM by Rizky Alfikri Rachmat
 */
class NetworkRepositoryImpl(
    private val apiService: ApiService
) : NetworkRepository {

    override suspend fun searchGames(queryGame: String): Flow<MainState<List<GameInfoModel>>> =
        flow {
            try {
                val response = apiService.searchGames("a51ef38e6d754bdc919a4104b0387fa3", queryGame)
                val games = response.gameDataResults
                when {
                    games.isNullOrEmpty() -> emit(MainState.Empty)
                    else -> emit(MainState.Success(data = games))
                }
            } catch (e: Exception) {
                emit(MainState.Failed(errorMessage = e.message))
            }
        }.flowOn(Dispatchers.IO)
}
