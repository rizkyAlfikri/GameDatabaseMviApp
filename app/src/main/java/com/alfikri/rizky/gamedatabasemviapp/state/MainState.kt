package com.alfikri.rizky.gamedatabasemviapp.state

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MainState, v 0.1 11/8/2021 9:32 PM by Rizky Alfikri Rachmat
 */
sealed class MainState<T>(val data: T? = null, val errorMessage: String? = null) {

    class Loading<T> : MainState<T>()
    class Idle<T> : MainState<T>()
    class Empty<T>: MainState<T>()
    class Success<T>(data: T?) : MainState<T>(data = data)
    class Failed<T>(errorMessage: String?) : MainState<T>(errorMessage = errorMessage)
}
