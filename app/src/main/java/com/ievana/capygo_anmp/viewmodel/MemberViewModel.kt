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
            Team("1","EVOS Legends", "EVOS Esports (briefly known as Zero Latitude) is a professional esports organisation based in Jakarta, Indonesia. It has competitive teams in Arena of Valor, Apex Legends, Free Fire, Mobile Legends: Bang Bang, PUBG Mobile and League of Legends: Wild Rift.", 10, "https://robohash.org/teamAlpha",
                arrayListOf(
                    Member(
                        "1",
                        "Toni",
                        "Support",
                        "https://robohash.org/toni"
                    )
                )
            ),
            Team("2","Fnatic", "In February 2021 Fnatic entered Valorant by picking up mix team SUMN FC. The successful European players joined the Black and Orange to form Fnatic’s first-ever VALORANT roster.", 5, "https://robohash.org/teamBeta",
                arrayListOf(
                    Member(
                        "1",
                        "Joni",
                        "Support",
                        "https://robohash.org/joni"
                    )
                )
            ),
            Team("3", "Team Liquid", "Team Liquid is a professional esports organization founded in the Netherlands in 2000. Originally a Brood War clan, the team switched to StarCraft II during the SC2 Beta in 2010, and became one of the most successful foreign teams. On August 7, 2020, Team Liquid entered VALORANT by signing fish123.", 10, "https://robohash.org/teamGamma",
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