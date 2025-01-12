package com.ievana.capygo_anmp.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ievana.capygo_anmp.model.Game
import com.ievana.capygo_anmp.model.Member
import com.ievana.capygo_anmp.model.Team
import com.ievana.capygo_anmp.util.buildDb
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext


class MemberViewModel(application: Application):AndroidViewModel(application), CoroutineScope {
    //untuk page who we are
    val membersLD = MutableLiveData<List<Team>>()
    val memberLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()
    private var job = Job()

    fun fetchTeam(){
        memberLoadErrorLD.value = false
        loadingLD.value = true
        launch {
            val db = buildDb(getApplication())
            val teams = db.gameDao().selectTeam()
            membersLD.postValue(teams)
            Log.d("TeamViewModel", "Teams: ${teams}")
            loadingLD.postValue(false)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.IO

    fun refresh(gameName:String){

//        membersLD.value =
//            arrayListOf(
//            Team("1","Valorant","EVOS Legends", 10, "https://robohash.org/teamAlpha",
//                teamMember = arrayListOf(
//                    Member(
//                        "1",
//                        "Latansa",
//                        "Support",
//                        "https://robohash.org/toni"
//                    ),Member(
//                            "2",
//                    "Ievana",
//                    "Attacker",
//                    "https://robohash.org/rina"
//                ),
//                Member(
//                    "3",
//                    "Jeane",
//                    "Attacker",
//                    "https://robohash.org/emma"
//                )
//                )
//            ),
//            Team("2","Genshin Impact","Fnatic",  5, "https://robohash.org/teamBeta",
//                teamMember = arrayListOf(
//                    Member(
//                        "1",
//                        "Joni",
//                        "Support",
//                        "https://robohash.org/joni"
//                    ),Member(
//                        "2",
//                        "Toni",
//                        "Attacker",
//                        "https://robohash.org/rina"
//                    ),
//                    Member(
//                        "3",
//                        "Riko",
//                        "Attacker",
//                        "https://robohash.org/emma"
//                    )
//                )
//            ),
//            Team("3", "Mobile Legends","Team Liquid is a professional esports organization founded in the Netherlands in 2000. Originally a Brood War clan, the team switched to StarCraft II during the SC2 Beta in 2010, and became one of the most successful foreign teams. On August 7, 2020, Team Liquid entered VALORANT by signing fish123.", 10, "https://robohash.org/teamGamma",
//                teamMember =  arrayListOf(
//                    Member(
//                        "1",
//                        "Josh",
//                        "Sniper",
//                        "https://robohash.org/josh"
//                    ),
//                    Member(
//                        "2",
//                        "Rina",
//                        "Attacker",
//                        "https://robohash.org/rina"
//                    ),
//                    Member(
//                        "3",
//                        "Emma",
//                        "Attacker",
//                        "https://robohash.org/emma"
//                    )
//                )
//            )
//        )

        memberLoadErrorLD.value = false
        loadingLD.value = false

    }
}