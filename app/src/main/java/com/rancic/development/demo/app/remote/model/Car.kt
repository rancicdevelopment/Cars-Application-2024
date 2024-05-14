package com.rancic.development.demo.app.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


class Car : Serializable {

    @SerializedName("id")
    val id: Int? = null

    @SerializedName("title")
    var title: String? = null

    @SerializedName("description")
    var description: String? = null

    @SerializedName("year")
    var year: Int? = null

    @SerializedName("urlToImage")
    var urlToImage: String? = null

    @SerializedName("url")
    var url: String? = null

}