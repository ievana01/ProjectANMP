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
//    var id: String,
    @ColumnInfo(name = "name")
    var name: String?,
    @ColumnInfo(name = "description")
    var description: String?,
    @ColumnInfo(name = "image")
    var image: String?,
    var teams: ArrayList<Team>,
    var achievements : List<Achievement>
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

@Entity
data class Achievement(
    @ColumnInfo(name = "year")
    var year: Int?,
    @ColumnInfo(name = "winningTeam")
    var winningTeam: String?,//nama team
    @ColumnInfo(name = "competitionTitle")
    var competitionTitle: String? //list competition
)

@Entity
data class Team(
    //untuk team page dan who we are
//    var id:String,
    @ColumnInfo(name = "gameName")
    var gameName:String,
    @ColumnInfo(name = "teamName")
    var teamName: String,
    @ColumnInfo(name = "desc")
    var desc:String, //who we are
    @ColumnInfo(name = "like")
    var like: Int, //who we are
    @ColumnInfo(name = "imageTeam")
    var imageTeam: String?,
    var teamMember:ArrayList<Member>,
){
    @PrimaryKey(autoGenerate = true)
    var idTeam:Int = 0
}

@Entity
data class Member(
    // untuk team page detail
//    var id:String,
    @ColumnInfo(name = "memberName")
    var memberName: String,
    @ColumnInfo(name = "role")
    var role: String,
    @ColumnInfo(name = "memberImage")
    var memberImage: String?
){
    @PrimaryKey(autoGenerate = true)
    var idMember:Int = 0
}

@Entity
data class Schedule(
//    var id: String,
    @ColumnInfo(name = "date")
    var date: String?,
    @ColumnInfo(name = "time")
    var time: String?,
    @ColumnInfo(name = "ytLs")
    @SerializedName("youtubeLiveStream")
    var ytLS:String?,
    @ColumnInfo(name = "gameName")
    var gameName: String?,
    @ColumnInfo(name = "team")
    var team: String?,
    @ColumnInfo(name = "location")
    var location:String?,
    @ColumnInfo(name = "desc")
    @SerializedName("eventDescription")
    var desc:String?,
    @ColumnInfo(name = "gameImage")
    var gameImage: String?
){
    @PrimaryKey(autoGenerate = true)
    var idSchedule:Int = 0
}




