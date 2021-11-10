package com.alfikri.rizky.gamedatabasemviapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alfikri.rizky.gamedatabasemviapp.data.MainRepository
import com.alfikri.rizky.gamedatabasemviapp.data.model.GameInfoModel
import com.alfikri.rizky.gamedatabasemviapp.intent.MainIntent
import com.alfikri.rizky.gamedatabasemviapp.state.MainState
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MainViewModel, v 0.1 11/8/2021 9:57 PM by Rizky Alfikri Rachmat
 */

@ExperimentalCoroutinesApi
class MainViewModel(
    private val mainRepository: MainRepository
) : ViewModel() {

    val queryGameIntent = Channel<MainIntent>(Channel.UNLIMITED)
    private val _queryGameState = MutableStateFlow<MainState<List<GameInfoModel>>>(MainState.Idle())
    val queryGameState: StateFlow<MainState<List<GameInfoModel>>>
        get() = _queryGameState

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            queryGameIntent.consumeAsFlow().collect {
                when (it) {
                    is MainIntent.FetchGames -> searchGame(it.queryGame)
                }
            }
        }
    }

    private fun searchGame(queryGame: String) {
        viewModelScope.launch {
            if (queryGame.isEmpty()) {
                _queryGameState.value = MainState.Idle()
            } else {
                _queryGameState.value = MainState.Loading()
                mainRepository.searchGames(queryGame).collect {
                    _queryGameState.value = it
                }
            }
        }
    }
}