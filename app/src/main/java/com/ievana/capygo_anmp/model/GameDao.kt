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

@Query("SELECT * FROM team INNER JOIN game ON team.idGame = game.id WHERE game.gameName = :gameName")
fun getTeam(gameName: String): List<Team>


}