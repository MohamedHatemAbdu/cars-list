package com.me.data.carlist.datasource

import com.me.domain.carlist.entity.CarEntity
import io.reactivex.rxjava3.core.Single


interface ICarRemoteDataSource {

    fun getCarsList(): Single<List<CarEntity>>
}