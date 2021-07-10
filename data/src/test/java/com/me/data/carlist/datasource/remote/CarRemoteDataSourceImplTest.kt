package com.me.data.carlist.datasource.remote


import com.me.data.carlist.carData
import com.me.data.carlist.datasource.remote.api.ICarApi
import io.reactivex.rxjava3.core.Single

import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class CarRemoteDataSourceImplTest {

    private lateinit var dataSource: CarRemoteDataSourceImpl

    private val mockApi: ICarApi = mock(ICarApi::class.java)

    private val remoteItem = carData.copy(modelName = "remote")

    private val throwable = Throwable()


    @Before
    fun setUp() {
        dataSource = CarRemoteDataSourceImpl(mockApi)

    }

    @Test
    fun `get car list remote success`() {
        // given
        `when`(mockApi.getCarsList()).thenReturn(
            Single.just(
                listOf(remoteItem)
            )
        )

        // when
        val test = dataSource.getCarsList().test()

        // then
        verify(mockApi).getCarsList()
        assert(test.values()[0][0].modelName == remoteItem.modelName)
    }

    @Test
    fun `get car list remote fail`() {
        // given
        `when`(mockApi.getCarsList()).thenReturn(Single.error(throwable))

        // when
        val test = dataSource.getCarsList().test()

        // then
        verify(mockApi).getCarsList()
        test.assertError(throwable)
    }

}