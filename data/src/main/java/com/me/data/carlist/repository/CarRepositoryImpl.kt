package com.me.data.carlist.repository

import com.me.data.carlist.datasource.ICarRemoteDataSource
import com.me.domain.carlist.entity.CarEntity
import com.me.domain.carlist.repository.ICarRepository
import io.reactivex.rxjava3.core.Single

class CarRepositoryImpl(
    val carRemoteDataSource: ICarRemoteDataSource
) : ICarRepository {

    override fun getCarsList(): Single<List<CarEntity>> = carRemoteDataSource.getCarsList()


}