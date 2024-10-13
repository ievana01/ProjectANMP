package com.ievana.capygo_anmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ievana.capygo_anmp.model.Game

class ListViewModel :ViewModel(){
    val gamesLD = MutableLiveData<ArrayList<Game>>()
    val gameLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh(){
        gamesLD.value = arrayListOf(
            Game("1", "Valorant", "Valorant is a free-to-play first-person tactical hero shooter developed and published by Riot Games. The game's development started in 2014 and was teased under the codename Project A in October 2019."),
            Game("2", "Mobile Legend", "Mobile Legends: Bang Bang (MLBB) is a mobile multiplayer online battle arena (MOBA) game developed and published by Chinese developer Moonton, a subsidiary of ByteDance. The game was released in 2016 and grew in popularity, most prominently in Southeast Asia."),
            Game("3", "PUBG", "PUBG: Battlegrounds is a 2017 battle royale game developed by PUBG Studios and published by Krafton. The game, which was inspired by 2000's Japanese film Battle Royale, is based on previous mods created by Brendan \"PlayerUnknown\" Greene for other games, and expanded into a standalone game under Greene's creative direction.")
        )

        gameLoadErrorLD.value = false
        loadingLD.value = false
    }
}