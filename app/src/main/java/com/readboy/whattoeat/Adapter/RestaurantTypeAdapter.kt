package com.readboy.whattoeat.Adapter

import android.content.Context
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.readboy.whattoeat.Fragment.FragmentRestaurantBig
import com.readboy.whattoeat.Fragment.FragmentRestaurantNormal

/**
 * Created by Ilystina on 2017/11/1.
 */
class RestaurantTypeAdapter(fm: FragmentManager, private val context: Context):FragmentPagerAdapter(fm){

    var typeName:Array<String> = arrayOf("普通","大餐")
    override fun getCount(): Int {
       return typeName.size
    }

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> return FragmentRestaurantNormal()
            1 -> return FragmentRestaurantBig()
        }
        return FragmentRestaurantNormal()
    }
}