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
            "CREATE TABLE 'team' ('idTeam' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'teamName' TEXT, 'description' TEXT, 'like' INTEGER, 'imageTeam' TEXT)"
        )
        database.execSQL(
            "CREATE TABLE 'member' ('idMember' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'idTeam' INTEGER, 'memberName' TEXT, 'role' TEXT,'memberImage' TEXT)"
        )

        database.execSQL(
            "CREATE TABLE 'achivement' ('idAch' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'idGame' INTEGER, 'year' TEXT, 'idTeam' INTEGER,'competitionTitle' TEXT)"
        )

        database.execSQL(
            "CREATE TABLE 'team_game' ('id' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, 'idTeam' INTEGER, 'idGame' INTEGER)"
        )

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
        "INSERT into 'game' (id,gameName, description,image) " +
                "VALUES (2, 'Valorant', 'Valorant is a 2020 first-person tactical hero shooter video game developed and published by Riot Games. A free-to-play game, Valorant takes inspiration from the Counter-Strike series, borrowing several mechanics such as the buy menu, spray patterns, and inaccuracy while moving. Development started in 2014 and was teased under the codename Project A in 2019; the game was released on June 2, 2020 for Windows. It was ported to the PlayStation 5 and Xbox Series X/S in June 2024, albeit without crossplay between PC and console clients.', " +
                "'https://upload.wikimedia.org/wikipedia/en/b/ba/Valorant_cover.jpg')"
    )

    database.execSQL(
        "INSERT into 'game' (id,gameName, description,image) " +
                "VALUES (3, 'Genshin Impact', 'Genshin Impact is a 2020 action role-playing video game produced by MiHoYo/HoYoverse. The game features an anime-style open world environment and an action-based battle system using elemental magic and character-switching. A free-to-play game monetized through gacha game mechanics, Genshin Impact is updated regularly using the games as a service model; it was originally released for Android, iOS, PlayStation 4, and Windows, followed by PlayStation 5 in 2021, with an Xbox Series X/S version in November 2024.', " +
                "'https://upload.wikimedia.org/wikipedia/en/thumb/5/5d/Genshin_Impact_logo.svg/330px-Genshin_Impact_logo.svg.png')"
    )


    database.execSQL(
        "INSERT into 'team' (idTeam, teamName, description, like, imageTeam) " +
                "VALUES (1, 'Team MoLe A', 'Team MoLe A sudah memiliki banyak prestasi di berbagai kompetisi Mobile Legends', 40, 'https://robohash.org/teamGamma')")

    database.execSQL(
        "INSERT into 'team' (idTeam, teamName, description, like, imageTeam) " +
                "VALUES (2, 'Team Genshin A', 'Team Genshin A sudah memiliki banyak prestasi di berbagai kompetisi', 35, 'https://robohash.org/teamgenshin')")

    database.execSQL(
        "INSERT into 'team' (idTeam, teamName, description, like, imageTeam) " +
                "VALUES (3, 'Team Valo A', 'Team Valo A sudah memiliki banyak prestasi di berbagai kompetisi', 20, 'https://robohash.org/teamvalo')")

    database.execSQL(
        "INSERT into 'team' (idTeam, teamName, description, like, imageTeam) " +
                "VALUES (4, 'Team SerbaBisa', 'Team SerbaBisa sudah memiliki banyak prestasi di berbagai kompetisi', 65, 'https://robohash.org/teamserba')")


    database.execSQL(
        "INSERT into 'team_game' (idTeam, idGame) " +
                "VALUES (4, 1)")


    database.execSQL(
        "INSERT into 'team_game' (idTeam, idGame) " +
                "VALUES (4, 2)")

    database.execSQL(
        "INSERT into 'team_game' (idTeam, idGame) " +
                "VALUES (1, 1)")

    database.execSQL(
        "INSERT into 'team_game' (idTeam, idGame) " +
                "VALUES (2, 3)")

    database.execSQL(
        "INSERT into 'team_game' (idTeam, idGame) " +
                "VALUES (3, 2)")

    database.execSQL(
        "INSERT into 'member' (idMember, idTeam, memberName, role, memberImage) VALUES (1, 1, 'Josh', 'Fighter', 'https://robohash.org/teamfighter')")

    database.execSQL(
        "INSERT into 'member' (idMember, idTeam, memberName, role, memberImage) VALUES (2, 2, 'Jack', 'Attack', 'https://robohash.org/teamattack')")

    database.execSQL(
        "INSERT into 'member' (idMember, idTeam, memberName, role, memberImage) VALUES (3, 3, 'Joni', 'Support', 'https://robohash.org/teamsupport')")

    database.execSQL(
        "INSERT into 'achivement' (idAch,idGame, year, idTeam,competitionTitle) VALUES (1, 1, '2023', 1, 'MPL Indonesia Season 15')")

    database.execSQL(
        "INSERT into 'achivement' (idAch,idGame, year, idTeam,competitionTitle) VALUES (4, 1, '2020', 4, 'MPL Indonesia Season 10')")
    database.execSQL(
        "INSERT into 'achivement' (idAch,idGame, year, idTeam,competitionTitle) VALUES (5, 1, '2015', 1, 'MPL Indonesia Season 5')")

    database.execSQL(
        "INSERT into 'achivement' (idAch,idGame, year, idTeam,competitionTitle) VALUES (2, 2, '2024', 2, 'Lomba Valorant')")
    database.execSQL(
        "INSERT into 'achivement' (idAch,idGame, year, idTeam,competitionTitle) VALUES (3, 3, '2021', 3, 'Lomba Genshin')")

}



