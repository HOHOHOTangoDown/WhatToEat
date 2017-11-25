package com.readboy.whattoeat.Activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.readboy.whattoeat.Adapter.RestaurantTypeAdapter
import com.readboy.whattoeat.R
import kotlinx.android.synthetic.main.activity_main.*

/**
 * Created by Ilystina on 2017/11/1.
 */
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupToolBar()
        initView()
    }

    private fun setupToolBar(){
        main_tool_bar.setLogo(R.mipmap.ic_launcher_round)
        main_tool_bar.title = "吃饭"
        main_tool_bar.inflateMenu(R.menu.main_menu)
        setSupportActionBar(main_tool_bar)
    }

    private fun initView(){
        viewPager_restaurant_info!!.adapter = RestaurantTypeAdapter(supportFragmentManager,this)
        tap_restaurant_type.setupWithViewPager(viewPager_restaurant_info)
        tap_restaurant_type.getTabAt(0)!!.setText(R.string.normal_dinner)
        tap_restaurant_type.getTabAt(0)!!.setIcon(R.mipmap.ic_launcher)
        tap_restaurant_type.getTabAt(1)!!.setText(R.string.big_dinner)
        tap_restaurant_type.getTabAt(1)!!.setIcon(R.mipmap.ic_launcher)
    }

    private fun registTabEvent(){

    }

}
