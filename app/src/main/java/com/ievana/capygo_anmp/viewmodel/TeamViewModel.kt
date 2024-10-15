package com.ievana.capygo_anmp.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ievana.capygo_anmp.model.Member
import com.ievana.capygo_anmp.model.Team
import com.ievana.capygo_anmp.model.TeamList
import com.ievana.capygo_anmp.model.TeamMember

class TeamViewModel:ViewModel() {
    val teamsLD = MutableLiveData<ArrayList<Team>>()
    val teamLoadErrorLD = MutableLiveData<Boolean>()
    val loadingLD = MutableLiveData<Boolean>()

    fun refresh(){
        teamsLD.value = arrayListOf(
            // Valorant
            Team(
                gameName = "Valorant",
                teamList = arrayListOf(
                    TeamList(
                        id = "T01",
                        name = "Team Phoenix",
                        member = arrayListOf(
                            TeamMember(id = "M01", name = "Alice", role = "Leader"),
                            TeamMember(id = "M02", name = "Bob", role = "Sniper"),
                            TeamMember(id = "M03", name = "Charlie", role = "Support")
                        )
                    ),
                    TeamList(
                        id = "T02",
                        name = "Team Shadow",
                        member = arrayListOf(
                            TeamMember(id = "M04", name = "David", role = "Leader"),
                            TeamMember(id = "M05", name = "Eva", role = "Assaulter"),
                            TeamMember(id = "M06", name = "Frank", role = "Defender")
                        )
                    ),
                    TeamList(
                        id = "T03",
                        name = "Team Phantom",
                        member = arrayListOf(
                            TeamMember(id = "M07", name = "Grace", role = "Leader"),
                            TeamMember(id = "M08", name = "Harry", role = "Strategist"),
                            TeamMember(id = "M09", name = "Ivy", role = "Sniper")
                        )
                    )
                )
            ),
            // Mobile Legends
            Team(
                gameName = "Mobile Legends",
                teamList = arrayListOf(
                    TeamList(
                        id = "T01",
                        name = "Team Phoenix",  // Tim yang sama dengan game Valorant
                        member = arrayListOf(
                            TeamMember(id = "M01", name = "Alice", role = "Leader"),
                            TeamMember(id = "M02", name = "Bob", role = "Mage"),
                            TeamMember(id = "M03", name = "Charlie", role = "Tank")
                        )
                    ),
                    TeamList(
                        id = "T04",
                        name = "Team Dragon",
                        member = arrayListOf(
                            TeamMember(id = "M10", name = "Jack", role = "Leader"),
                            TeamMember(id = "M11", name = "Kim", role = "Marksman"),
                            TeamMember(id = "M12", name = "Liam", role = "Support")
                        )
                    ),
                    TeamList(
                        id = "T05",
                        name = "Team Blaze",
                        member = arrayListOf(
                            TeamMember(id = "M13", name = "Mia", role = "Leader"),
                            TeamMember(id = "M14", name = "Noah", role = "Fighter"),
                            TeamMember(id = "M15", name = "Oscar", role = "Tank")
                        )
                    )
                )
            ),
            // Genshin Impact
            Team(
                gameName = "Genshin Impact",
                teamList = arrayListOf(
                    TeamList(
                        id = "T01",
                        name = "Team Phoenix",  // Tim yang sama dengan game Valorant dan Mobile Legends
                        member = arrayListOf(
                            TeamMember(id = "M01", name = "Alice", role = "Leader"),
                            TeamMember(id = "M02", name = "Bob", role = "DPS"),
                            TeamMember(id = "M03", name = "Charlie", role = "Support")
                        )
                    ),
                    TeamList(
                        id = "T06",
                        name = "Team Storm",
                        member = arrayListOf(
                            TeamMember(id = "M16", name = "Peter", role = "Leader"),
                            TeamMember(id = "M17", name = "Quinn", role = "Healer"),
                            TeamMember(id = "M18", name = "Rita", role = "DPS")
                        )
                    ),
                    TeamList(
                        id = "T07",
                        name = "Team Thunder",
                        member = arrayListOf(
                            TeamMember(id = "M19", name = "Steve", role = "Leader"),
                            TeamMember(id = "M20", name = "Tina", role = "DPS"),
                            TeamMember(id = "M21", name = "Uma", role = "Support")
                        )
                    )
                )
            )
        )



    }


}