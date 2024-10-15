package com.ievana.capygo_anmp.model

data class Game(
    val name: String?,
    val description: String?,
    val image: String?
)

data class Achievement(
    val year: Int?,
    val winningTeam: String?,
    val competitionTitle: String?
)

data class Member(
    var id:String,
    var name: String,
    var desc: String,
    var like: Int
)

data class Team(
    var gameName: String,
    var gameURL:String,
    var teamList: ArrayList<TeamList>
)

data class TeamList(
    var id:String,
    var name: String,
    var member: ArrayList<TeamMember>
)
data class TeamMember(
    var id:String,
    var name:String,
    var role: String,
    var profileURL:String

)