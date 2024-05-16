package com.rancic.development.demo.app.mapping

import com.rancic.development.demo.app.local.entity.CarEntity
import com.rancic.development.demo.app.remote.model.Car


object CarMapper : Mapper<CarEntity, Car> {
    override fun fromRemote(r: Car): CarEntity {
        return CarEntity(
            id = r.id,
            description = r.description,
            url = r.url,
            year = r.year,
            title = r.title,
            urlToImage = r.urlToImage,
            category = r.category
        )
    }

    override fun fromLocal(l: CarEntity): Car {
        return Car(
            id = l.id,
            description = l.description,
            year = l.year,
            urlToImage = l.urlToImage,
            url = l.url,
            title = l.title,
            category = l.category
        )
    }
}