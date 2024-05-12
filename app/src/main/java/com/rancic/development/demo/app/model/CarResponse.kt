package com.rancic.development.demo.app.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

class CarResponse : Serializable{

    @SerializedName("response")
    var response: Boolean? = null

    @SerializedName("data")
    val data: List<Car>? = null

    @SerializedName("message")
    var message: String? = null

}

