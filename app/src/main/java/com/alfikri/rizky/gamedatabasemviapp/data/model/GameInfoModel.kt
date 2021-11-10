package com.alfikri.rizky.gamedatabasemviapp.data.model

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.Json

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version GameInfoModel, v 0.1 11/8/2021 7:53 PM by Rizky Alfikri Rachmat
 */
data class GameInfoModel(
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("released")
    val released: String?,
    @SerializedName("background_image")
    val backgroundImage: String?,
    @SerializedName("rating")
    val rating: Double?,
)
