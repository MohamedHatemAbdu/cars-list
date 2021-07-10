package com.me.domain.carlist.usecase


import com.me.domain.carlist.carEntity
import com.me.domain.carlist.repository.ICarRepository
import io.reactivex.rxjava3.core.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.*


class CarUseCaseTest {

    private lateinit var carUseCase: CarUseCase
    private val mockCarRepository: ICarRepository = mock(ICarRepository::class.java)

    private val carsList = listOf(carEntity)

    private val throwable = Throwable()

    @Before
    fun setUp() {
        carUseCase = CarUseCase(mockCarRepository)
    }

    @Test
    fun `repository get cars list success`() {
        // given
        `when`(mockCarRepository.getCarsList()).thenReturn(Single.just(carsList))

        // when
        val test = carUseCase.getCarsList().test()

        // test
        verify(mockCarRepository).getCarsList()

        test.assertNoErrors()
        test.assertComplete()
        test.assertValueCount(1)
        test.assertValue(carsList)
    }

    @Test
    fun `repository get cars list fail`() {
        // given
        `when`(mockCarRepository.getCarsList()).thenReturn(Single.error(throwable))

        // when
        val test = carUseCase.getCarsList().test()

        //
        // then
        verify(mockCarRepository).getCarsList()

        test.assertNoValues()
        test.assertNotComplete()
        test.assertError(throwable)
        test.assertValueCount(0)
    }


}

