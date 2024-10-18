package com.ievana.capygo_anmp.model

import com.google.gson.annotations.SerializedName
import java.util.stream.BaseStream

data class Game(
    var id: String,
    var name: String?,
    var description: String?,
    var image: String?,
    var teams: ArrayList<Team>,
    var achievements : List<Achievement>
)

data class Achievement(
    var year: Int?,
    var winningTeam: String?,//nama team
    var competitionTitle: String? //list competition
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

data class Schedule(
    var id: String,
    var date: String?,
    var time: String?,
    @SerializedName("youtubeLiveStream")
    var ytLS:String?,
    var gameName: String?,
    var team: String?,
    var location:String?,
    @SerializedName("eventDescription")
    var desc:String?,
    var gameImage: String?
)




