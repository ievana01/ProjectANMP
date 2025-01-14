package com.ievana.capygo_anmp.model

import androidx.room.Dao
import androidx.room.Query

@Dao
interface GameDao {
    @Query("SELECT * FROM game")
    fun selectGame(): List<Game>

    @Query("SELECT * FROM achivement")
    fun selectAch(): List<Achievement>


    @Query("SELECT * FROM team")
    fun selectTeam(): List<Team>

    @Query("SELECT * FROM team_game INNER JOIN team ON team_game.idTeam = team.idTeam WHERE idGame = :idGame")
    fun getTeam(idGame: Int): List<Team>

    @Query("SELECT game.id, game.image FROM game INNER JOIN team_game ON team_game.idGame = game.id WHERE game.id = :idGame GROUP BY game.id, game.image")
    fun getImage(idGame: Int): List<Game>

    @Query("SELECT game.id, game.image FROM game INNER JOIN team_game ON team_game.idGame = game.id WHERE team_game.idTeam = :idTeam GROUP BY game.id, game.image")
    fun getImageMember(idTeam: Int): List<Game>

    @Query("SELECT * FROM member INNER JOIN team_game ON team_game.idTeam = member.idTeam WHERE member.idTeam=:idTeam GROUP BY member.idMember")
    fun getMember(idTeam: Int): List<Member>

    @Query("UPDATE team SET `like`=:like WHERE idTeam=:idTeam")
    fun updateLikeTeam(like:Int, idTeam: Int)

    @Query("SELECT * FROM achivement INNER JOIN game ON achivement.idGame = game.id WHERE game.id = :idGame")
    fun getAch(idGame: Int): List<Achievement>

    @Query("SELECT * FROM achivement INNER JOIN game ON achivement.idGame = game.id WHERE game.id = :idGame AND achivement.year=:selecYear")
    fun getAchYear(idGame: Int, selecYear:String): List<Achievement>

    @Query("SELECT * FROM schedule")
    fun getAllSchedules(): List<Schedule>

    @Query("SELECT * FROM game WHERE id = :idGame")
    fun getGameById(idGame: Int):Game

    @Query("SELECT * FROM schedule WHERE id = :idSchedule")
    fun getScheduleById(idSchedule:Int):Schedule
}