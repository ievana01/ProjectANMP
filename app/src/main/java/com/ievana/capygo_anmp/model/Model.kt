package com.ievana.capygo_anmp.model

data class Game(
    var id: String,
    var name: String?,
    var description: String?,
    var image: String?,
    var teams: ArrayList<Team>
)

data class Achievement(
    var year: Int?,
    var winningTeam: String?,
    var competitionTitle: String?
)
data class Team(
    //untuk team page dan who we are
    var id:String,
    var teamName: String,
    var desc:String, //who we are
    var like: Int, //who we are
    var imageTeam: String?,
    var teamMember:ArrayList<Member>,
)

data class Member(
    // untuk team page detail
    var id:String,
    var memberName: String,
    var role: String,
    var memberImage: String?
)




