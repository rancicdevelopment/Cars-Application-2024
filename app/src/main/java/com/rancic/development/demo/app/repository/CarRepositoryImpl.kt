package com.rancic.development.demo.app.repository

import com.rancic.development.demo.app.common.Error
import com.rancic.development.demo.app.common.Result
import com.rancic.development.demo.app.remote.model.Car
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val dataSource: CarRemoteDataSource,
    private val ioDispatcher: CoroutineDispatcher
) : CarRepository {

    override suspend fun getCars(category: String): Flow<Result<List<Car>>> {
        return flow {
            emit(Result.loading())
            val result = dataSource.getCars(category)
            if (result.status == Result.Status.SUCCESS) {
                result.data?.let {
                    val userResponse = it
                    emit(Result.success(userResponse))
                }
            } else if (result.status == Result.Status.ERROR) {
                emit(
                    Result.error(
                        error = result.error ?: Error.UNKNOWN_ERROR,
                        data = result.data
                    )
                )
            }
        }.flowOn(ioDispatcher)
    }

}