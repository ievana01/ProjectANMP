package com.ievana.capygo_anmp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ievana.capygo_anmp.util.DB_NAME
import com.ievana.capygo_anmp.util.MIGRATION_1_2
import com.ievana.capygo_anmp.util.populateSeedData


@Database(entities = arrayOf(Game::class, Member::class, Team::class, Achievement::class), version = 2)
abstract class GameDatabase: RoomDatabase() {
    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var instance: GameDatabase? = null
        private val LOCK = Any()

        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                GameDatabase::class.java,
                DB_NAME
            ).addMigrations(MIGRATION_1_2)
                .addCallback(object : RoomDatabase.Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        // Memanggil fungsi untuk menambahkan seed data saat database pertama kali dibuat
                        populateSeedData(db)
                    }
                })
                .build()

        operator fun invoke(context: Context):GameDatabase {
//            if (instance == null) {
//                synchronized(LOCK) {
//                    instance ?: buildDatabase(context).also {
//                        instance = it
//                    }
//                }
//            }
            return instance ?: synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }

    }
}


