package com.rancic.development.demo.app.repository

import com.rancic.development.demo.app.common.Result
import com.rancic.development.demo.app.remote.model.Car
import kotlinx.coroutines.flow.Flow

interface CarRepository {

    suspend fun getCars(category: String): Flow<Result<List<Car>>>

}
