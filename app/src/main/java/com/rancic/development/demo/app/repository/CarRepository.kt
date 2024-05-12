package com.rancic.development.demo.app.repository

import com.rancic.development.demo.app.model.Car
import kotlinx.coroutines.flow.Flow
import com.rancic.development.demo.app.common.Result

interface CarRepository {

    suspend fun allCars(): Flow<Result<List<Car>>>

}
