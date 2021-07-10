package com.me.domain.carlist.usecase


import com.me.domain.carlist.entity.CarEntity
import com.me.domain.carlist.repository.ICarRepository
import io.reactivex.rxjava3.core.Single


class CarUseCase constructor(
    private val carRepository: ICarRepository
) {

    fun getCarsList(): Single<List<CarEntity>> =
        carRepository.getCarsList()

}


