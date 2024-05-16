package com.rancic.development.demo.app.remote.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable


data class Car(

    @SerializedName("id")
    var id: Int? = null,

    @SerializedName("title")
    var title: String? = null,

    @SerializedName("description")
    var description: String? = null,

    @SerializedName("year")
    var year: Int? = null,

    @SerializedName("urlToImage")
    var urlToImage: String? = null,

    @SerializedName("url")
    var url: String? = null,

    @SerializedName("category")
    val category: String? = null

) : Serializable