package com.me.presentation.carlist.view.activity

import android.os.Bundle
import android.view.Menu
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.ui.NavigationUI
import com.me.presentation.R
import com.me.presentation.base.viewmodel.ViewModelFactory
import com.me.presentation.carlist.view.viewmodel.CarViewModel
import dagger.android.AndroidInjection
import kotlinx.android.synthetic.main.activity_cars.*
import javax.inject.Inject


class CarsActivity : AppCompatActivity() {


    @Inject
    lateinit var vmCarFactory: ViewModelFactory<CarViewModel>

    private val vmCar by lazy {
        ViewModelProviders.of(this, vmCarFactory)
            .get(CarViewModel::class.java)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_cars)
        setupNavigation()

        vmCar.getCarsList()
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        return false
    }

    private fun setupNavigation() {
        val navController = findNavController(R.id.nav_host)
        NavigationUI.setupWithNavController(btmNavigationViewMenuItems, navController)
    }

    override fun onSupportNavigateUp(): Boolean =
        Navigation.findNavController(this, R.id.nav_host).navigateUp()


}
