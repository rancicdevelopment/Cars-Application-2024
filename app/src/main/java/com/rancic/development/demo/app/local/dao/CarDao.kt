package com.rancic.development.demo.app.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.rancic.development.demo.app.local.entity.CarEntity
import com.rancic.development.demo.app.remote.model.Car
import com.rancic.development.demo.app.ui.components.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CarDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(cars: List<CarEntity>)

    @Query("SELECT * FROM cars WHERE category = :category")
    fun getCars(category: String): List<CarEntity>

    @Query("DELETE FROM cars")
    fun clearCars()

}