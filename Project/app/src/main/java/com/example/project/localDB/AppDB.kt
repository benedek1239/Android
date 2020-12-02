package com.example.project.localDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@Database(entities = [Profile::class], version = 1)
abstract class AppDB : RoomDatabase() {
    abstract fun DAO(): DAO?

    companion object {
        @Volatile
        private var INSTANCE: AppDB? = null
        private const val NUMBER_OF_THREADS = 1
        val databaseWriteExecutor: ExecutorService =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS)

        fun getDatabase(context: Context): AppDB? {
            if (INSTANCE == null) {
                synchronized(AppDB::class.java) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(
                            context,
                            AppDB::class.java, "user_database"
                        )
                            .build()
                    }
                }
            }
            return INSTANCE
        }
    }

}