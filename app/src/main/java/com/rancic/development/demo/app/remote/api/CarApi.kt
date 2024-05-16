package com.rancic.development.demo.app.remote.api

import com.rancic.development.demo.app.remote.model.CarResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface CarApi {

    @GET("api/cars/{category}/cars.json")
    suspend fun getCars(@Path("category") category: String): CarResponse

}