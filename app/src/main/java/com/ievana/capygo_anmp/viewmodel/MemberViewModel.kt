package com.ievana.capygo_anmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ievana.capygo_anmp.model.Game
import com.ievana.capygo_anmp.model.Member
import com.ievana.capygo_anmp.model.Team


class MemberViewModel:ViewModel() {
    //untuk page who we are
    val membersLD = MutableLiveData<ArrayList<Team>>()
    val memberLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh(){
        membersLD.value = arrayListOf(
            Team("1","EVOS", "EVOS Esports (briefly known as Zero Latitude) is a professional esports organisation based in Jakarta, Indonesia. It has competitive teams in Arena of Valor, Apex Legends, Free Fire, Mobile Legends: Bang Bang, PUBG Mobile and League of Legends: Wild Rift.", 10, "https://robohash.org/teamAlpha",
                arrayListOf(
                    Member(
                        "1",
                        "Toni",
                        "Support",
                        "https://robohash.org/toni"
                    )
                )
            ),
            Team("2","PMSL-EMEA", "PUBG Mobile Super League - EMEA (also known as PMSL EMEA) is the official professional PUBG Mobile partnership league held across Europe, Middle East and Africa.", 5, "",
                arrayListOf(
                    Member(
                        "1",
                        "Joni",
                        "Support",
                        "https://robohash.org/joni"
                    )
                )
            ),
            Team("3", "G2 ESPORT", "G2 Esports entered VALORANT in June 2020, and have since become one of the most successful European organizations in the game, winning every Ignition Series event in the region.", 10, "https://robohash.org/teamGamma",
                arrayListOf(
                    Member(
                        "1",
                        "Josh",
                        "Sniper",
                        "https://robohash.org/josh"
                    ),
                    Member(
                        "2",
                        "Rina",
                        "Attacker",
                        "https://robohash.org/rina"
                    ),
                    Member(
                        "3",
                        "Emma",
                        "Attacker",
                        "https://robohash.org/emma"
                    )
                )
            )
        )

        memberLoadErrorLD.value = false
        loadingLD.value = false
    }
}