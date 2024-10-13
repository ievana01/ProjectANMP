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