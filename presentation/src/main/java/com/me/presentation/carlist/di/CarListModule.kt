package com.me.presentation.carlist.di


import com.me.data.base.ServiceGenerator
import com.me.data.carlist.datasource.ICarRemoteDataSource
import com.me.data.carlist.datasource.remote.CarRemoteDataSourceImpl
import com.me.data.carlist.datasource.remote.api.ICarApi
import com.me.data.carlist.repository.CarRepositoryImpl
import com.me.domain.carlist.repository.ICarRepository
import com.me.domain.carlist.usecase.CarUseCase
import com.me.presentation.BuildConfig
import com.me.presentation.carlist.view.adapter.CarListAdapter
import com.me.presentation.carlist.view.viewmodel.CarViewModel
import dagger.Module
import dagger.Provides


@Module
class CarListModule {

    @Provides
    fun provideCarServiceAPIs(): ICarApi =
        ServiceGenerator.createService(ICarApi::class.java, BuildConfig.DEBUG)


    @Provides
    fun provideCarRemoteDataSource(
        carApi: ICarApi
    ): ICarRemoteDataSource =
        CarRemoteDataSourceImpl(carApi)


    @Provides
    fun provideCarRepository(
        carRemoteDataSource: ICarRemoteDataSource
    ): ICarRepository =
        CarRepositoryImpl(
            carRemoteDataSource
        )


    @Provides
    fun provideCarUseCase(carRepository: ICarRepository): CarUseCase =
        CarUseCase(carRepository)

    @Provides
    fun provideCarListViewModel(
        carUseCase: CarUseCase
    ): CarViewModel =
        CarViewModel(
            carUseCase
        )

    @Provides
    fun provideCarListAdapter(): CarListAdapter {
        return CarListAdapter()
    }


}