package com.me.data.carlist.datasource.remote.api

import com.me.data.carlist.model.CarData
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET

interface ICarApi {

    @GET("codingtask/cars")
    fun getCarsList(): Single<List<CarData>>

}
