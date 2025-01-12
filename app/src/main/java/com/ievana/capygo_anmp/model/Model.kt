package com.ievana.capygo_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.util.stream.BaseStream


@Entity
data class User(
    @ColumnInfo(name="firstname")
    var firstName:String,
    @ColumnInfo(name="lastname")
    var lastName:String,
    @ColumnInfo(name="username")
    var username:String,
    @ColumnInfo(name="password")
    var password:String
) {
    @PrimaryKey(autoGenerate = true)
    var uuid:Int =0
}


@Entity
data class Proposal(
    @ColumnInfo(name="gameName")
    var gameName:String,
    @ColumnInfo(name="teamName")
    var teamName:String,
    @ColumnInfo(name="desc")
    var desc:String,
    @ColumnInfo(name="status")
    var status:String,
) {
    @PrimaryKey(autoGenerate = true)
    var id_prop:Int =0
}

@Entity
data class Game(
    @ColumnInfo(name="gameName")
    var name: String?,
    @ColumnInfo(name="description")
    var description: String?,
    @ColumnInfo(name="image")
    var image: String?,
    var teams: ArrayList<Team>,
    var achievements : List<Achievement>
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

data class Achievement(
    var year: Int?,
    var winningTeam: String?,//nama team
    var competitionTitle: String? //list competition
)

data class Team(
    //untuk team page dan who we are
    var id:String,
    var gameName:String,
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




