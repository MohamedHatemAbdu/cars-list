package com.me.presentation.carlist.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.LatLngBounds
import com.google.android.gms.maps.model.MarkerOptions
import com.me.domain.carlist.entity.CarEntity
import com.me.presentation.R
import com.me.presentation.base.model.Resource
import com.me.presentation.base.viewmodel.ViewModelFactory
import com.me.presentation.carlist.view.viewmodel.CarViewModel
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject


class CarMapFragment : Fragment(), OnMapReadyCallback {


    @Inject
    lateinit var vmFactory: ViewModelFactory<CarViewModel>

    private lateinit var vm: CarViewModel
    private lateinit var carsMap: GoogleMap


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        activity?.let { notNullAct ->
            vm = ViewModelProviders.of(notNullAct, vmFactory)
                .get(CarViewModel::class.java)
        }



        return inflater.inflate(R.layout.fragment_cars_map, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUiWidgets()
    }

    private fun initUiWidgets() {

        val mapFragment =
            childFragmentManager.findFragmentById(R.id.carsMapFragment) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    private fun initUiEngines() {
        vm.mlCarsList.observe(this, Observer { updateCarsList(it) })

    }

    private fun updateCarsList(resource: Resource<List<CarEntity>>) {
        resource?.let {
            it.data?.let { carsList ->
                drawCarsMarkers(carsList)
            }

        }
    }


    private fun drawCarsMarkers(carsList: List<CarEntity>) {
        if (carsList.isNotEmpty()) {

            val builder = LatLngBounds.Builder()
            for (car in carsList) {
                val point = LatLng(car.latitude, car.longitude)
                val markerOptions = MarkerOptions()
                    .position(point)
                    .title(car.name)
                    .snippet(
                        getString(
                            R.string.car_item_car_maker_group_series_label,
                            car.make,
                            car.series
                        )
                    )
                carsMap.addMarker(markerOptions)
                builder.include(point)
            }

            val bounds = builder.build()
            val padding = 300
            val cameraUpdate = CameraUpdateFactory.newLatLngBounds(bounds, padding)
            carsMap.animateCamera(cameraUpdate)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        carsMap = googleMap
        initUiEngines()
    }
}
