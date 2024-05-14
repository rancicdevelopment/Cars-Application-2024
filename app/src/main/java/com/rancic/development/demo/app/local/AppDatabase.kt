package com.rancic.development.demo.app.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.rancic.development.demo.app.local.dao.CarDao
import com.rancic.development.demo.app.local.entity.CarEntity

@Database(
    entities = [
        CarEntity::class,
        /* some another entity class, ... */
    ], version = AppDatabase.DB_VERSION
)
abstract class AppDatabase : RoomDatabase() {


    companion object {
        const val DB_VERSION: Int = 1
        const val DB_NAME = "AppDatabase"
    }


    abstract fun carDao(): CarDao

}