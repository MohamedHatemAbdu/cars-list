package com.me.presentation.carlist.view.adapter

import androidx.recyclerview.widget.DiffUtil
import com.me.domain.carlist.entity.CarEntity
import com.me.presentation.R
import com.me.presentation.base.adapter.DataBindingAdapter


class CarListAdapter : DataBindingAdapter<CarEntity>(
    DiffCallback()
) {

    class DiffCallback : DiffUtil.ItemCallback<CarEntity>() {
        override fun areItemsTheSame(oldItem: CarEntity, newItem: CarEntity): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: CarEntity, newItem: CarEntity): Boolean =
            oldItem == newItem
    }

    override fun getItemViewType(position: Int) = R.layout.car_item
}