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
    fun getTeam(idGame: Int): List<TeamGame>


    @Query("SELECT * FROM member INNER JOIN team_game ON team_game.idTeam = member.idTeam WHERE idGame=:idGame")
    fun getMember(idGame: Int): List<Member>

    @Query("UPDATE team SET `like`=:like WHERE idTeam=:idTeam")
    fun updateLikeTeam(like:Int, idTeam: Int)

    @Query("SELECT * FROM achivement INNER JOIN game ON achivement.idGame = game.id WHERE game.id = :idGame")
    fun getAch(idGame: Int): List<Achievement>

    @Query("SELECT * FROM achivement INNER JOIN game ON achivement.idGame = game.id WHERE game.id = :idGame AND achivement.year=:selecYear")
    fun getAchYear(idGame: Int, selecYear:String): List<Achievement>
}