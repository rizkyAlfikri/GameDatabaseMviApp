package com.alfikri.rizky.gamedatabasemviapp.intent

/**
 * @author Rizky Alfikri Rachmat (rizkyalfikri@gmail.com)
 * @version MainIntent, v 0.1 11/8/2021 9:56 PM by Rizky Alfikri Rachmat
 */
sealed class MainIntent {

    class FetchGames(val queryGame: String): MainIntent()
}