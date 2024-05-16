package com.rancic.development.demo.app.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.rancic.development.demo.app.ui.components.model.Category

@Entity( tableName = "cars")
data class CarEntity(
   @PrimaryKey val id: Int? = null,
   val category: String? = null,
   val description: String? = null,
   val year: Int? = null,
   val title: String? = null,
   val urlToImage: String? = null,
   val url: String? = null
) {

}