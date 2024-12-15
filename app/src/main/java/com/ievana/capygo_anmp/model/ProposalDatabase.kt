package com.ievana.capygo_anmp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Proposal::class), version = 1)
abstract class PropDatabase: RoomDatabase() {
    abstract fun propDao(): ProposalDao

    companion object {
        @Volatile
        private var instance: PropDatabase? = null
        private val LOCK = Any()
        fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                PropDatabase::class.java,
                "newpropdb"
            ).build()
    }

    operator fun invoke(context: Context) {
        if (instance == null) {
            synchronized(LOCK) {
                instance ?: buildDatabase(context).also {
                    instance = it
                }
            }
        }
    }
}