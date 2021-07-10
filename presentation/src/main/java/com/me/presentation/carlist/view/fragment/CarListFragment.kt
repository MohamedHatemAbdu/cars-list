package com.me.presentation.carlist.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.OvershootInterpolator
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.snackbar.Snackbar
import com.me.domain.carlist.entity.CarEntity
import com.me.presentation.R
import com.me.presentation.base.extensions.startRefreshing
import com.me.presentation.base.extensions.stopRefreshing
import com.me.presentation.base.model.Resource
import com.me.presentation.base.model.ResourceState
import com.me.presentation.base.viewmodel.ViewModelFactory
import com.me.presentation.carlist.view.adapter.CarListAdapter
import com.me.presentation.carlist.view.viewmodel.CarViewModel
import dagger.android.support.AndroidSupportInjection
import jp.wasabeef.recyclerview.animators.SlideInUpAnimator
import kotlinx.android.synthetic.main.fragment_cars_list.*
import javax.inject.Inject


class CarListFragment : Fragment() {


    @Inject
    lateinit var adapter: CarListAdapter

    @Inject
    lateinit var vmFactory: ViewModelFactory<CarViewModel>

    private lateinit var vm: CarViewModel

    private val snackBar by lazy {
        Snackbar.make(
            srlCarList,
            getString(R.string.network_error),
            Snackbar.LENGTH_INDEFINITE
        )
    }


    override fun onAttach(context: Context) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_cars_list, container, false)

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        activity?.let { notNullAct ->
            vm = ViewModelProviders.of(notNullAct, vmFactory)
                .get(CarViewModel::class.java)
        }

        rvCarList.adapter = adapter
        rvCarList.itemAnimator = SlideInUpAnimator(OvershootInterpolator(3f))

        vm.mlCarsList.observe(this, Observer { updateCarsList(it) })

        srlCarList.setOnRefreshListener {
            vm.getCarsList()
        }

    }


    private fun updateCarsList(resource: Resource<List<CarEntity>>?) {
        resource?.let {
            updateLoadingState(it.state)

            it.data?.let { carsList ->
                adapter.submitList(carsList)
            }
            it.message?.let { showSnackBar(getString(R.string.network_error)) }
        }
    }

    private fun updateLoadingState(state: ResourceState) {
        when (state) {
            ResourceState.LOADING -> srlCarList.startRefreshing()
            ResourceState.SUCCESS -> srlCarList.stopRefreshing()
            ResourceState.ERROR -> srlCarList.stopRefreshing()
        }
    }

    private fun showSnackBar(msg: String) {
        snackBar.setAction(getString(R.string.car_list_snack_bar_action)) {
            snackBar.dismiss()
        }.setText(msg).show()
    }
}
