package com.ievana.capygo_anmp.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
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

@Entity(tableName = "game")
data class Game(
    @ColumnInfo(name="gameName")
    var name: String?,
    @ColumnInfo(name="description")
    var description: String?,
    @ColumnInfo(name="image")
    var image: String?,
//    var teams: ArrayList<Team>,
//    var achievements : List<Achievement>
){
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}

@Entity(tableName = "achivement")
data class Achievement(
    @ColumnInfo(name="idGame")
    val gameId: Int,
    @ColumnInfo(name="idTeam")
    var winningTeam: Int?,//nama team

    @ColumnInfo(name="year")
    var year: Int?,

    @ColumnInfo(name="competitionTitle")
    var competitionTitle: String? //list competition
){
    @PrimaryKey(autoGenerate = true)
    var idAch:Int = 0
}

@Entity(tableName = "team")
data class Team(
    @ColumnInfo(name="teamName")
    var teamName: String,
    @ColumnInfo(name="description")
    var desc:String, //who we are
    @ColumnInfo(name="like")
    var like: Int, //who we are
    @ColumnInfo(name="imageTeam")
    var imageTeam: String?,
//    var teamMember:ArrayList<Member>,
)
{
    @PrimaryKey(autoGenerate = true)
    var idTeam:Int = 0
}

@Entity(tableName = "team_game")
data class TeamGame(
    @ColumnInfo(name = "idTeam")
    var idTeam: Int,  // ID tim dari tabel 'team'

    @ColumnInfo(name = "idGame")
    var idGame: Int,  // ID game dari tabel 'game'
){
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}




@Entity(tableName = "member")
data class Member(
    @ColumnInfo(name="idTeam")
    var teamId: Int,

    @ColumnInfo(name="memberName")
    var memberName: String,
    @ColumnInfo(name="role")
    var role: String,
    @ColumnInfo(name="memberImage")
    var memberImage: String?
){
    @PrimaryKey(autoGenerate = true)
    var idMember: Int=0
}

@Entity(tableName = "schedule")
data class Schedule(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name="date")
    var date: String?,
    @ColumnInfo(name="time")
    var time: String?,
    @ColumnInfo(name="ytLS")
    var ytLS:String?,
    @ColumnInfo(name="idGame")
    var idGame: Int,
    @ColumnInfo(name="team")
    var team: String?,
    @ColumnInfo(name="location")
    var location:String?,
    @ColumnInfo(name="description")
    var description:String?
)





