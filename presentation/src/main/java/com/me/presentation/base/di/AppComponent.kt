package com.me.presentation.base.di

import android.app.Application
import com.me.presentation.base.App
import com.me.presentation.carlist.di.CarViewBuilder
import dagger.BindsInstance
import dagger.Component
import dagger.android.support.AndroidSupportInjectionModule

import javax.inject.Singleton

@Singleton
@Component(modules = [AndroidSupportInjectionModule::class, AppModule::class, CarViewBuilder::class])
interface AppComponent {

    fun inject(app: App)

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent

    }

}
