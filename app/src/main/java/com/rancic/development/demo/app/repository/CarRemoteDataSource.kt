package com.rancic.development.demo.app.repository

import com.google.gson.Gson
import com.rancic.development.demo.app.BuildConfig

import com.rancic.development.demo.app.common.Error
import com.rancic.development.demo.app.common.Result
import com.rancic.development.demo.app.local.dao.CarDao
import com.rancic.development.demo.app.mapping.CarMapper
import com.rancic.development.demo.app.remote.api.CarApi
import com.rancic.development.demo.app.remote.model.Car
import com.rancic.development.demo.app.remote.model.CarResponse
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CarRemoteDataSource @Inject constructor(
    private val carApi: CarApi,
    private val carDao: CarDao
) {

    suspend fun getCars(category: String): Result<List<Car>> {
        return try {
            val result = carApi.getCars(category)

            result.data?.let { list ->
                val localList = list.map { car -> CarMapper.fromRemote(car) }
                carDao.insertAll(localList)
            }

            Result.success(result.data)
        } catch (e: Exception) {
            if (e is HttpException) {
                val errorBody = e.response()?.errorBody()?.string()
                try {
                    val error = Gson().fromJson(errorBody, CarResponse::class.java)
                    Result.error(Error(400, message = error.message))
                } catch (e: IOException) {
                    Result.error(Error(message = e.localizedMessage))
                } catch (e: Exception) {
                    Result.error(Error(message = e.localizedMessage))
                }
            } else {
                val offline = e.localizedMessage!!.contains(BuildConfig.OFFLINE_ERROR_MSG)
                if (offline) {
                    val localResult = carDao.getCars(category)

                    val list = localResult.map { carEntity -> CarMapper.fromLocal(carEntity) }

                    Result.success(list)
                } else {
                    Result.error(Error(message = e.localizedMessage))
                }
            }
        }
    }
}