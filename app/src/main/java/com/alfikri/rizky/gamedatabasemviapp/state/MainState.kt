package com.alfikri.rizky.gamedatabasemviapp.state

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MainState, v 0.1 11/8/2021 9:32 PM by Rizky Alfikri Rachmat
 */
sealed class MainState<out T>(val data: T? = null, val errorMessage: String? = null) {

    object Loading : MainState<Nothing>()
    object Idle: MainState<Nothing>()
    object Empty: MainState<Nothing>()
    class Success<T>(data: T?) : MainState<T>(data = data)
    class Failed(errorMessage: String?) : MainState<Nothing>(errorMessage = errorMessage)
}
