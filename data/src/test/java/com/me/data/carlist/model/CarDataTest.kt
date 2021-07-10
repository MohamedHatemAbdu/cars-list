package com.me.data.carlist.model

import com.me.data.carlist.carData
import org.junit.Test

class CarDataTest {


    @Test
    fun `map data to domain`() {
        // given

        // when
        val model = carData.mapToDomain()

        // then
        assert(model.id == carData.id)
        assert(model.modelName == carData.modelName)
        assert(model.name == carData.name)
        assert(model.make == carData.make)
        assert(model.group == carData.group)
        assert(model.color == carData.color)
        assert(model.series == carData.series)
        assert(model.fuelType == carData.fuelType)
        assert(model.fuelLevel == carData.fuelLevel)
        assert(model.transmission == carData.transmission)
        assert(model.licensePlate == carData.licensePlate)
        assert(model.longitude == carData.longitude)
        assert(model.innerCleanliness == carData.innerCleanliness)
        assert(model.carImageUrl == carData.carImageUrl)

    }
}