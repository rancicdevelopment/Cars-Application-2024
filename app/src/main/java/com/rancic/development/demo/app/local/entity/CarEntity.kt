package com.rancic.development.demo.app.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity( tableName = "cars")
data class CarEntity(
   @PrimaryKey val id: Int? = null
)