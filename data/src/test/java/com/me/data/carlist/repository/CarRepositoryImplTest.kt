package com.me.data.carlist.repository

import com.me.data.carlist.carEntity
import com.me.data.carlist.datasource.remote.CarRemoteDataSourceImpl
import io.reactivex.rxjava3.core.Single

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*

class CarRepositoryImplTest {

    private lateinit var repository: CarRepositoryImpl


    private val mockRemoteDataSource: CarRemoteDataSourceImpl =
        mock(CarRemoteDataSourceImpl::class.java)


    private val remoteItem = carEntity.copy(modelName = "remote")


    private val throwable = Throwable()

    @Before
    fun setUp() {
        repository = CarRepositoryImpl(mockRemoteDataSource)
    }


    @Test
    fun `get car list remote success`() {
        // given
        `when`(mockRemoteDataSource.getCarsList()).thenReturn(Single.just(listOf(remoteItem)))

        // when
        val test = repository.getCarsList().test()

        // then
        verify(mockRemoteDataSource).getCarsList()
        assert(test.values()[0][0].modelName == remoteItem.modelName)
    }

    @Test
    fun `get car list remote fail`() {
        // given
        `when`(mockRemoteDataSource.getCarsList()).thenReturn(Single.error(throwable))

        // when
        val test = repository.getCarsList().test()

        // then
        verify(mockRemoteDataSource).getCarsList()
        test.assertError(throwable)
    }


}