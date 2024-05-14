package com.rancic.development.demo.app.local.dao

import androidx.room.Dao
import androidx.room.Query
import com.rancic.development.demo.app.local.entity.CarEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Query("SELECT * FROM cars")
    fun getCars(): Flow<List<CarEntity>>

}