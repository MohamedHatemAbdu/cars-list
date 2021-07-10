package com.me.data.carlist.model

import com.me.domain.carlist.entity.CarEntity
import com.squareup.moshi.Json

data class CarData(

    @field:Json(name = "id")
    val id: String = "",

    @field:Json(name = "modelName")
    val modelName: String = "",

    @field:Json(name = "name")
    val name: String = "",

    @field:Json(name = "make")
    val make: String = "",

    @field:Json(name = "group")
    val group: String = "",

    @field:Json(name = "color")
    val color: String = "",

    @field:Json(name = "series")
    val series: String = "",

    @field:Json(name = "fuelType")
    val fuelType: String = "",

    @field:Json(name = "fuelLevel")
    val fuelLevel: Double = 0.0,

    @field:Json(name = "transmission")
    val transmission: String = "",

    @field:Json(name = "licensePlate")
    val licensePlate: String = "",

    @field:Json(name = "latitude")
    val latitude: Double = 0.0,

    @field:Json(name = "longitude")
    val longitude: Double = 0.0,

    @field:Json(name = "innerCleanliness")
    val innerCleanliness: String = "",

    @field:Json(name = "carImageUrl")
    val carImageUrl: String = ""
)

fun CarData.mapToDomain(): CarEntity =
    CarEntity(
        id,
        modelName,
        name,
        make,
        group,
        color,
        series,
        fuelType,
        fuelLevel,
        transmission,
        licensePlate,
        latitude,
        longitude,
        innerCleanliness,
        carImageUrl
    )

fun List<CarData>.mapToDomain(): List<CarEntity> = map { it.mapToDomain() }

fun CarEntity.mapToData(): CarData =
    CarData(
        id,
        modelName,
        name,
        make,
        group,
        color,
        series,
        fuelType,
        fuelLevel,
        transmission,
        licensePlate,
        latitude,
        longitude,
        innerCleanliness,
        carImageUrl    )

fun List<CarEntity>.mapToData(): List<CarData> = map { it.mapToData() }
