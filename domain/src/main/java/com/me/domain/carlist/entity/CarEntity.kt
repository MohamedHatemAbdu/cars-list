package com.me.domain.carlist.entity

data class CarEntity(

    val id: String = "",
    val modelName: String = "",
    val name: String = "",
    val make: String = "",
    val group: String = "",
    val color: String = "",
    val series: String = "",
    val fuelType: String = "",
    val fuelLevel: Double = 0.0,
    val transmission: String = "",
    val licensePlate: String = "",
    val latitude: Double = 0.0,
    val longitude: Double = 0.0,
    val innerCleanliness: String = "",
    val carImageUrl: String = ""
)