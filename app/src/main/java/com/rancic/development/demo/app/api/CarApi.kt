package com.rancic.development.demo.app.api

import com.rancic.development.demo.app.model.CarResponse
import retrofit2.http.GET
import retrofit2.http.Path


interface CarApi {

    @GET("/cars.json")
    suspend fun getCars(): CarResponse


}