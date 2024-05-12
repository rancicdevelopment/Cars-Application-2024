package com.rancic.development.demo.app.repository

import com.rancic.development.demo.app.model.Car
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import com.rancic.development.demo.app.common.Result
import com.rancic.development.demo.app.common.Error
import kotlinx.coroutines.flow.flowOn

class CarRepositoryImpl @Inject constructor(
    private val dataSource: CarRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : CarRepository{


    override suspend fun allCars(): Flow<com.rancic.development.demo.app.common.Result<List<Car>>> {
        return flow {
            emit(com.rancic.development.demo.app.common.Result.loading())
            val result = dataSource.load()
            if (result.status == com.rancic.development.demo.app.common.Result.Status.SUCCESS) {
                result.data?.let {
                    val userResponse = it
                    emit(com.rancic.development.demo.app.common.Result.success(userResponse))
                }
            } else if (result.status == com.rancic.development.demo.app.common.Result.Status.ERROR) {
                emit(
                    com.rancic.development.demo.app.common.Result.error(
                        error = result.error ?: Error.UNKNOWN_ERROR,
                        data = result.data
                    )
                )
            }
        }.flowOn(ioDispatcher)
    }

}