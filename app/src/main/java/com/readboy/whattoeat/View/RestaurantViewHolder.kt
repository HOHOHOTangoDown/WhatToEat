package com.readboy.whattoeat.View

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.item_restaurant.view.*

/**
 * Created by Ilystina on 2017/11/2.
 */
class RestaurantViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
    val name:TextView = itemView.restaurant_name
}