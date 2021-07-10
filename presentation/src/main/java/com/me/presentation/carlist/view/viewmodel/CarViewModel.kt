package com.me.presentation.carlist.view.viewmodel

import androidx.lifecycle.MutableLiveData
import com.me.domain.carlist.entity.CarEntity
import com.me.domain.carlist.usecase.CarUseCase
import com.me.presentation.base.extensions.setError
import com.me.presentation.base.extensions.setLoading
import com.me.presentation.base.extensions.setSuccess
import com.me.presentation.base.model.Resource
import com.me.presentation.base.viewmodel.BaseViewModel
import io.reactivex.rxjava3.schedulers.Schedulers

class CarViewModel constructor(private val carUseCase: CarUseCase) :
    BaseViewModel() {

    val mlCarsList = MutableLiveData<Resource<List<CarEntity>>>()

    fun getCarsList() =
        compositeDisposable.add(
            carUseCase.getCarsList()
                .doOnSubscribe { mlCarsList.setLoading() }
                .subscribeOn(Schedulers.io())
                .subscribe({
                    mlCarsList.setSuccess(it)
                }, {
                    mlCarsList.setError(it.message)
                })
        )


}