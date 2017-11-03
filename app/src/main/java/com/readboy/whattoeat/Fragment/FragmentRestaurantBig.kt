package com.readboy.whattoeat.Fragment

import android.graphics.Color
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.readboy.whattoeat.Adapter.RestaurantListAdapter
import com.readboy.whattoeat.DataRecord.RestaurantStore
import kotlinx.android.synthetic.main.fragment_restaurant_big.*
import com.readboy.whattoeat.R
import kotlinx.android.synthetic.main.item_restaurant.view.*
import java.util.*

/**
 * Created by Ilystina on 2017/11/2.
 */
class FragmentRestaurantBig:Fragment(){
    var restaurants:ArrayList<String> = ArrayList<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurants = RestaurantStore.getInstance().getBigList()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_restaurant_big,container,false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initResListView()
        registerEvent()
    }
    private fun initResListView(){
        var manager = LinearLayoutManager(activity)
        restaurant_list.layoutManager = manager
        var adapter = RestaurantListAdapter(restaurants){view, s ->
            view.restaurant_name.setTextColor(Color.RED)
        }
        restaurant_list.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun registerEvent(){
        btn_save.setOnClickListener { v -> saveRestaurant()}
        btn_start_roll.setOnClickListener{v -> startRoll() }
    }

    private fun saveRestaurant(){
        var name:String = restaurant_to_record.text.toString()
        if(name.isEmpty()){
            Toast.makeText(activity,"请输入餐厅名字", Toast.LENGTH_SHORT).show()
            return
        }
        restaurant_to_record.setText("")
        RestaurantStore.getInstance().insertRestaurantBig(name)
        restaurants.add(name)
        restaurant_list.adapter.notifyDataSetChanged()
    }

    private fun startRoll(){
        if (restaurants.size < 1) {
            Toast.makeText(activity,"暂时没有餐厅收录", Toast.LENGTH_SHORT).show()
            return
        }
        var random = Random()
        var index = random.nextInt(restaurants.size)
        if (index < restaurants.size) {
            roll_result.text = restaurants[index]
        } else {
            Toast.makeText(activity, "随机出错：index is" + index, Toast.LENGTH_SHORT).show()
        }
    }
}