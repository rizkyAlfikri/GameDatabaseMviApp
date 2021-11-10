package com.alfikri.rizky.gamedatabasemviapp.data.model

import com.google.gson.annotations.SerializedName

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameResponseModel, v 0.1 11/8/2021 7:56 PM by Rizky Alfikri Rachmat
 */
data class GameResponseModel(
    @SerializedName("results")
    val gameDataResults: List<GameInfoModel>?,
)
