package com.me.domain.carlist.repository

import com.me.domain.carlist.entity.CarEntity
import io.reactivex.rxjava3.core.Single


interface ICarRepository {

    fun getCarsList(): Single<List<CarEntity>>

}