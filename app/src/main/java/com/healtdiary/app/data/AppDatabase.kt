package com.healtdiary.app.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

/**
 * The Room database for this app
 */
@Database(entities = [Data::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun dataDao(): DataDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "HealthDiary"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}