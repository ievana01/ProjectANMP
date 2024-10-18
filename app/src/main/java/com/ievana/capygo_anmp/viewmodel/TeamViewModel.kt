package com.ievana.capygo_anmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

import com.ievana.capygo_anmp.model.Team

class TeamViewModel:ViewModel() {
    //untuk page team
    val teamsLD = MutableLiveData<ArrayList<Team>>()
    val teamLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh(){
        teamsLD.value = arrayListOf(


        )



    }


}