package com.example.project.localDB

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {
    @get:Query("SELECT * FROM Profile")
    val getAll: List<Profile?>?

    @Insert
    fun insertAll(vararg id: Profile)

    @Query("DELETE FROM Profile WHERE uid=:id")
    fun deleteById(id :Int)
}