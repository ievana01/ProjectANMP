package com.ievana.capygo_anmp.util


import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ievana.capygo_anmp.model.CapyGoDatabase
import com.ievana.capygo_anmp.model.GameDatabase


val DB_NAME = "capyEsport"


fun buildDb(context: Context):GameDatabase {
    val db = Room.databaseBuilder(context,
        GameDatabase::class.java, DB_NAME)
        .addMigrations(MIGRATION_1_2)
        .addCallback(object : RoomDatabase.Callback() {
            override fun onCreate(database: SupportSQLiteDatabase) {
                super.onCreate(database)
                populateSeedData(database)
            }
        })
        .build()

    return db
}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE 'user' ('uuid' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'firstname' TEXT , 'lastname' TEXT,'username' TEXT NOT NULL, 'password' TEXT NOT NULL)"
        )

        database.execSQL(
            "CREATE TABLE 'game' ( 'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , 'gameName' TEXT, 'description' TEXT, 'image' TEXT)"
        )
        database.execSQL(
            "CREATE TABLE 'team' ('idTeam' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'idGame' INTEGER, 'teamName' TEXT, 'description' TEXT, 'like' INTEGER, 'imageTeam' TEXT)"
        )
        database.execSQL(
            "CREATE TABLE 'member' ('idMember' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'idTeam' INTEGER, 'memberName' TEXT, 'role' TEXT,'memberImage' TEXT)"
        )

        database.execSQL(
            "CREATE TABLE 'achivement' ('idAch' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'idGame' INTEGER, 'year' TEXT, 'idTeam' INTEGER,'competitionTitle' TEXT)"
        )

//        database.execSQL(
//            "CREATE TABLE 'game' ( 'id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL , 'gameName' TEXT, 'description' TEXT, 'image' TEXT)")
//        database.execSQL(
//            "CREATE TABLE 'team' ('idTeam' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'idGame' INTEGER, 'teamName' TEXT, 'description' TEXT, 'like' INTEGER, 'imageTeam' TEXT)")
//        database.execSQL(
//            "CREATE TABLE 'member' ('idMember' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'idTeam' INTEGER, 'memberName' TEXT, 'role' TEXT,'memberImage' TEXT)")
//
//        database.execSQL(
//            "CREATE TABLE 'achivement' ('idAch' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'idGame' INTEGER, 'year' TEXT, 'winningTeam' TEXT,'competitionTitle' TEXT)")
    }

}
fun populateSeedData(database: SupportSQLiteDatabase) {
    database.execSQL(
        "INSERT into 'game' (id,gameName, description,image) " +
                "VALUES (1, 'Mobile Legends', 'Mobile Legends: Bang Bang (MLBB) is a mobile multiplayer online battle arena (MOBA) game developed and published by Chinese developer Moonton, a subsidiary of ByteDance. The game was released in 2016 and grew in popularity, most prominently in Southeast Asia.', " +
                "'https://upload.wikimedia.org/wikipedia/commons/thumb/1/14/Mobile_Legends_Bang_Bang_logo.png/330px-Mobile_Legends_Bang_Bang_logo.png')"
                )
    database.execSQL(
        "INSERT into 'team' (idTeam, idGame, teamName, description, like, imageTeam) " +
                "VALUES (1, 1, 'Team MoLe A', 'Team MoLe A sudah memiliki banyak prestasi di berbagai kompetisi Mobile Legends', 40, 'https://robohash.org/teamGamma')")

    database.execSQL(
        "INSERT into 'member' (idMember, idTeam, memberName, role, memberImage) VALUES (1, 1, 'Josh', 'Fighter', 'https://robohash.org/teamGamma')")


    database.execSQL(
        "INSERT into 'achivement' (idAch,idGame, year, idTeam,competitionTitle) VALUES (1, 1, '2023', 1, 'MPL Indonesia Season 15')")

}



