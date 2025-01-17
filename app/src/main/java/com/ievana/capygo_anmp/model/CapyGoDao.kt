package com.ievana.capygo_anmp.model

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface CapyGoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg user:User)

    @Query("SELECT * FROM user")
    fun selectAllTodo(): List<User>
    @Query("SELECT * FROM user WHERE uuid= :id")
    fun selectTodo(id:Int): User
    @Query("SELECT * FROM user WHERE username= :username AND password = :password")
    fun loginUser(username:String, password:String): User

    @Query("SELECT * FROM user WHERE username = :username LIMIT 1")
    fun getUserByUsername(username: String): User?

}