package com.me.presentation.base.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable

open class BaseViewModel : ViewModel() {

    protected val compositeDisposable = CompositeDisposable()


    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
}