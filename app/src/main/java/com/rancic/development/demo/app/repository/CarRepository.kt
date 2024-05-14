package com.rancic.development.demo.app.repository

import com.rancic.development.demo.app.common.Result
import com.rancic.development.demo.app.model.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    suspend fun allCars(): Flow<Result<List<Car>>>

}
