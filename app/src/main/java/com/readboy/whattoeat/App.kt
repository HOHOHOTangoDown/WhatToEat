package com.readboy.whattoeat

import android.app.Application
import android.content.Context

/**
 * Created by Ilystina on 2017/11/1.
 */
class App:Application(){
    override fun onCreate() {
        super.onCreate()
        appContext = this
    }

    //类似于java中的静态变量
    companion object {
        lateinit var appContext: Context
    }

}