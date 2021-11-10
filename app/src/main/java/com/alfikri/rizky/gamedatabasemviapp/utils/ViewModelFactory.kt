package com.alfikri.rizky.gamedatabasemviapp.utils

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.alfikri.rizky.gamedatabasemviapp.data.MainRepository
import com.alfikri.rizky.gamedatabasemviapp.data.datasource.NetworkRepository
import com.alfikri.rizky.gamedatabasemviapp.ui.MainViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import java.lang.IllegalArgumentException

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version ViewModelFactory, v 0.1 11/8/2021 10:14 PM by Rizky Alfikri Rachmat
 */
class ViewModelFactory(
    private val networkRepository: NetworkRepository
): ViewModelProvider.Factory {

    @ExperimentalCoroutinesApi
    override fun <T : ViewModel?> create(modelClass: Class<T>): T =
        when {
            modelClass.isAssignableFrom(MainViewModel::class.java) -> {
                MainViewModel(MainRepository(networkRepository)) as T
            }

            else -> throw IllegalArgumentException("Unknown class name")
        }
}