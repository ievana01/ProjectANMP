package com.ievana.capygo_anmp.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = arrayOf(User::class), version = 1)
abstract class CapyGoDatabase: RoomDatabase() {
        abstract fun capygoDao(): CapyGoDao
        companion object {
            @Volatile private var instance: CapyGoDatabase ?= null
            private val LOCK = Any()
            fun buildDatabase(context: Context) =
                Room.databaseBuilder(
                    context.applicationContext,
                    CapyGoDatabase::class.java,
                    "newcapygodb").build()
        }

        operator fun invoke(context:Context) {
            if(instance == null) {
                synchronized(LOCK) {
                    instance ?: buildDatabase(context).also {
                        instance = it
                    }
                }
            }
        }
    }


