package com.alfikri.rizky.gamedatabasemviapp.data.datasource

import com.alfikri.rizky.gamedatabasemviapp.data.model.GameInfoModel
import com.alfikri.rizky.gamedatabasemviapp.state.MainState
import kotlinx.coroutines.flow.Flow

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version NetworkRepository, v 0.1 11/8/2021 8:03 PM by Rizky Alfikri Rachmat
 */
interface NetworkRepository {

    suspend fun searchGames(queryGame: String): Flow<MainState<List<GameInfoModel>>>
}