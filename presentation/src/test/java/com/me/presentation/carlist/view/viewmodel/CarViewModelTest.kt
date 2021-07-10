package com.me.presentation.carlist.view.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.me.domain.carlist.usecase.CarUseCase
import com.me.presentation.RxImmediateSchedulerRule
import com.me.presentation.base.model.Resource
import com.me.presentation.base.model.ResourceState
import com.me.presentation.carEntity
import io.reactivex.rxjava3.core.Single
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mockito.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


class CarViewModelTest {

    private lateinit var viewModel: CarViewModel

    private val mockCarUseCase: CarUseCase = mock(CarUseCase::class.java)

    private val carsList = listOf(carEntity)

    private val throwable = Throwable()

    @Rule
    @JvmField
    val rxImmediateSchedulerRule = RxImmediateSchedulerRule()

    @Rule
    @JvmField
    val instantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        viewModel = CarViewModel(mockCarUseCase)
    }


    @Test
    fun `get car list succeeds`() {
        // given
        whenever(mockCarUseCase.getCarsList())
            .thenReturn(Single.just(carsList))

        // when
        viewModel.getCarsList()

        // then

        verify(mockCarUseCase).getCarsList()
        Assert.assertEquals(
            Resource(ResourceState.SUCCESS, carsList, null),
            viewModel.mlCarsList.value
        )
    }

    @Test
    fun `get car list fails`() {
        // given
        whenever(mockCarUseCase.getCarsList())
            .thenReturn(Single.error(throwable))

        // whe
        viewModel.getCarsList()

        // then
        verify(mockCarUseCase).getCarsList()
        Assert.assertEquals(
            Resource(ResourceState.ERROR, null, throwable.message),
            viewModel.mlCarsList.value
        )
    }


}