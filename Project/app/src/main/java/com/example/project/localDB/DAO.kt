package com.example.project.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface DAO {
    @get:Query("SELECT * FROM Profile")
    val getAll: List<Profile?>?

    @Insert
    fun insertAll(vararg id: Profile)
}