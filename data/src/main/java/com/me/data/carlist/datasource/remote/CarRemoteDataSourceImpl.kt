package com.me.data.carlist.datasource.remote


import com.me.data.carlist.datasource.ICarRemoteDataSource
import com.me.data.carlist.datasource.remote.api.ICarApi
import com.me.data.carlist.model.mapToDomain
import com.me.domain.carlist.entity.CarEntity
import io.reactivex.rxjava3.core.Single


class CarRemoteDataSourceImpl constructor(private val api: ICarApi) :
    ICarRemoteDataSource {

    override fun getCarsList(): Single<List<CarEntity>> =
        api.getCarsList().map {
            it.mapToDomain()
        }


}