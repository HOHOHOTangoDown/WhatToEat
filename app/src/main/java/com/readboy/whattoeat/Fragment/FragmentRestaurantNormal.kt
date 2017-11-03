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
import com.readboy.whattoeat.R
import kotlinx.android.synthetic.main.fragment_restaurant_norma.*
import kotlinx.android.synthetic.main.item_restaurant.view.*
import java.util.*


/**
 * Created by Ilystina on 2017/11/1.
 */
class FragmentRestaurantNormal : Fragment() {
    var restaurants: ArrayList<String> = ArrayList()
    var region: Int = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        restaurants = RestaurantStore.getInstance().getNormalListTangjia()
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater!!.inflate(R.layout.fragment_restaurant_norma, container, false)
        return view
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        radio_tangjia.isChecked = true
        initResListView()
        registerEvent()
    }

    private fun initResListView() {
        var manager = LinearLayoutManager(activity)
        restaurant_list.layoutManager = manager
        var adapter = RestaurantListAdapter(restaurants) { view: View, item: String ->

            view.setOnClickListener { v ->
                v.restaurant_name.setTextColor(Color.RED)
            }


        }

        restaurant_list.adapter = adapter
        adapter.notifyDataSetChanged()
    }

    private fun registerEvent() {
        btn_save.setOnClickListener { v -> saveRestaurant() }
        btn_start_roll.setOnClickListener { v -> startRoll() }
        btn_clear.setOnClickListener { v -> clearData() }
        radio_region.setOnCheckedChangeListener { group, checkedId ->
            restaurants.clear()
            when (checkedId) {
                R.id.radio_tangjia -> {
                    region = 1
                    restaurants = RestaurantStore.getInstance().getNormalListTangjia()
                    restaurant_list.adapter.notifyDataSetChanged()
                }
                R.id.radio_life_regoin -> {
                    region = 2
                    restaurants = RestaurantStore.getInstance().getNormalListLifeRegion()
                    restaurant_list.adapter.notifyDataSetChanged()

                }
            }
        }
    }

    private fun saveRestaurant() {
        var name: String = restaurant_to_record.text.toString()
        if (name.isEmpty()) {
            Toast.makeText(activity, "请输入餐厅名字", Toast.LENGTH_SHORT).show()
            return
        }
        restaurant_to_record.setText("")
        if (region == 1) {
            RestaurantStore.getInstance().insertRestaurantNormal(name)
        } else {
            RestaurantStore.getInstance().insertRestaurantNormalLifeRegion(name)
        }
        restaurants.add(name)
        restaurant_list.adapter.notifyDataSetChanged()
    }

    private fun startRoll() {
        if (restaurants.size < 1) {
            Toast.makeText(activity, "暂时没有餐厅收录", Toast.LENGTH_SHORT).show()
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

    private fun clearData() {
        when (region) {
            1 -> {
                restaurants.clear()
                RestaurantStore.getInstance().clearNormalTangjia()
                restaurant_list.adapter.notifyDataSetChanged()
            }
            2 -> {
                restaurants.clear()
                RestaurantStore.getInstance().clearNormalLifeRegion()
                restaurant_list.adapter.notifyDataSetChanged()
            }

        }
    }
}