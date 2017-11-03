package com.readboy.whattoeat.Adapter


import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import com.readboy.whattoeat.R
import com.readboy.whattoeat.View.RestaurantViewHolder

/**
 * Created by Ilystina on 2017/11/2.
 */
class RestaurantListAdapter<T>(val item: List<T>, val init: (View, T) -> Unit) : RecyclerView.Adapter<RestaurantViewHolder<T>>() {

    override fun onBindViewHolder(holder: RestaurantViewHolder<T>?, position: Int) {
        holder!!.name.text = item[position].toString()
        holder!!.bindForecast(item[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RestaurantViewHolder<T> {
        val view = View.inflate(parent!!.context, R.layout.item_restaurant, null)
        return RestaurantViewHolder(view, init)
    }

    override fun getItemCount(): Int {
        return item.count()
    }

}