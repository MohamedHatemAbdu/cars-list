package com.me.presentation.carlist.di

import com.me.presentation.carlist.view.activity.CarsActivity
import com.me.presentation.carlist.view.fragment.CarListFragment
import com.me.presentation.carlist.view.fragment.CarMapFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class CarViewBuilder {

    // activities //

    @ContributesAndroidInjector(modules = [CarListModule::class])
    abstract fun bindCarsActivity(): CarsActivity
    // activities //


    // Fragments //
    @ContributesAndroidInjector(modules = [CarListModule::class])
    abstract fun bindCarListFragment(): CarListFragment


    @ContributesAndroidInjector(modules = [CarListModule::class])
    abstract fun bindCarMapFragment(): CarMapFragment
    // Fragments //

}

