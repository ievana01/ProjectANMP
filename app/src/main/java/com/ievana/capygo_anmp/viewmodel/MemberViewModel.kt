package com.ievana.capygo_anmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ievana.capygo_anmp.model.Member

class MemberViewModel:ViewModel() {
    val membersLD = MutableLiveData<ArrayList<Member>>()
    val memberLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh(){
        membersLD.value = arrayListOf(
            Member("1", "EVOS", "EVOS Esports (briefly known as Zero Latitude) is a professional esports organisation based in Jakarta, Indonesia. It has competitive teams in Arena of Valor, Apex Legends, Free Fire, Mobile Legends: Bang Bang, PUBG Mobile and League of Legends: Wild Rift.", 10),
            Member("2","PMSL-EMEA", "PUBG Mobile Super League - EMEA (also known as PMSL EMEA) is the official professional PUBG Mobile partnership league held across Europe, Middle East and Africa.", 5),
            Member("3", "G2 ESPORT", "G2 Esports entered VALORANT in June 2020, and have since become one of the most successful European organizations in the game, winning every Ignition Series event in the region.", 10)
        )
        memberLoadErrorLD.value = false
        loadingLD.value = false
    }
}