package com.rancic.development.demo.app.repository

import com.google.gson.Gson
import com.rancic.development.demo.app.BuildConfig
import com.rancic.development.demo.app.api.CarApi
import com.rancic.development.demo.app.common.Error
import com.rancic.development.demo.app.common.Result
import com.rancic.development.demo.app.model.Car
import com.rancic.development.demo.app.model.CarResponse
import com.rancic.development.demo.app.util.SharedPref
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CarRemoteDataSource @Inject constructor(
    private val api: CarApi,
    private val sharedPref: SharedPref
) {
    suspend fun load(): Result<List<Car>> {
        return try {
            val result = api.getCars()

            // save to offline
            val jsonStr = Gson().toJson(result)

            // sharedPref.wr(projectId = projectId, jsonStr)

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
                    val jsonStr = sharedPref.token()
                    if (jsonStr == null) {
                        Result.success(listOf())
                    } else {
                        val result = Gson().fromJson(jsonStr, CarResponse::class.java)
                        Result.success(result.data)
                    }
                } else {
                    Result.error(Error(message = e.localizedMessage))
                }
            }
        }
    }
}