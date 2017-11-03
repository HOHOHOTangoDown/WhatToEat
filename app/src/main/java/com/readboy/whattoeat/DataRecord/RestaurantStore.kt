package com.readboy.whattoeat.DataRecord

import android.content.ContentValues
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.util.Log
import com.readboy.whattoeat.App
import java.sql.SQLException

/**
 * Created by Ilystina on 2017/11/1.
 */
class RestaurantStore {

    companion object {
        val DB_NAME: String = "restaurantStoreDb.db"
        var storeDb: SQLiteDatabase? = null

        @Volatile private var INSTANCE: RestaurantStore? = null
        fun getInstance(): RestaurantStore =
                INSTANCE ?: synchronized(this) {
                    INSTANCE ?: init()
                }

        private fun init(): RestaurantStore {
            var helper = DataBaseHelper(App.appContext, DB_NAME, 3)
            storeDb = helper.writableDatabase
            return RestaurantStore()
        }
    }

    val TAG = "RestaurantStore"
    val TABLE_NORMAL_DINNER: String = "TABLE_NORMAL_DINNER"
    val TABLE_BIG_DINNER: String = "TABLE_BIG_DINNER"
    val COL_RESTAURANT = "restaurant"
    val TABLE_NORMAL_LIFE_REGION:String = "TABLE_NORMAL_LIFE_REGION"

    fun insertRestaurantNormal(name: String) {
        try {
            synchronized(this) {
                storeDb!!.beginTransaction()
                var values = ContentValues()
                values.put(COL_RESTAURANT, name)
                storeDb!!.insert(TABLE_NORMAL_DINNER, null, values)
                storeDb!!.setTransactionSuccessful()
                storeDb!!.endTransaction()

            }
        } catch (e: SQLException) {
            Log.e(TAG, e.toString())
            e.printStackTrace()
        }
    }

    fun insertRestaurantNormalLifeRegion(name: String){
        try {
            synchronized(this) {
                storeDb!!.beginTransaction()
                var values = ContentValues()
                values.put(COL_RESTAURANT, name)
                storeDb!!.insert(TABLE_NORMAL_LIFE_REGION, null, values)
                storeDb!!.setTransactionSuccessful()
                storeDb!!.endTransaction()

            }
        } catch (e: SQLException) {
            Log.e(TAG, e.toString())
            e.printStackTrace()
        }
    }

    fun insertRestaurantBig(name: String) {
        try {
            synchronized(this) {
                storeDb!!.beginTransaction()
                var values = ContentValues()
                values.put(COL_RESTAURANT, name)
                storeDb!!.insert(TABLE_BIG_DINNER, null, values)
                storeDb!!.setTransactionSuccessful()
                storeDb!!.endTransaction()

            }
        } catch (e: SQLException) {
            Log.e(TAG, e.toString())
            e.printStackTrace()
        }
    }



    fun getNormalListTangjia(): ArrayList<String> {
        var list = ArrayList<String>()
        try {
            synchronized(this) {
                var cursor: Cursor
                var buffer = StringBuilder()
                buffer.append("select * from ").append(TABLE_NORMAL_DINNER)
                cursor = storeDb!!.rawQuery(buffer.toString(), null)
                if (cursor != null) {
                    while (cursor.moveToNext()){
                        list.add(cursor.getString(cursor.getColumnIndex(COL_RESTAURANT)))
                    }
                    cursor.close()
                }
            }

        } catch (e: SQLException) {
            Log.e(TAG, "get list error:" + e.toString())
            e.printStackTrace()
        }
        return list
    }

    fun getNormalListLifeRegion(): ArrayList<String>{
        var list = ArrayList<String>()
        try {
            synchronized(this) {
                var cursor: Cursor
                var buffer = StringBuilder()
                buffer.append("select * from ").append(TABLE_NORMAL_LIFE_REGION)
                cursor = storeDb!!.rawQuery(buffer.toString(), null)
                if (cursor != null) {
                    while (cursor.moveToNext()){
                        list.add(cursor.getString(cursor.getColumnIndex(COL_RESTAURANT)))
                    }
                    cursor.close()
                }
            }

        } catch (e: SQLException) {
            Log.e(TAG, "get list error:" + e.toString())
            e.printStackTrace()
        }
        return list
    }

    fun getBigList(): ArrayList<String> {
        var list = ArrayList<String>()
        try {
            synchronized(this) {
                var cursor: Cursor
                var buffer = StringBuilder()
                buffer.append("select * from ").append(TABLE_BIG_DINNER)
                cursor = storeDb!!.rawQuery(buffer.toString(), null)
                if (cursor != null) {
                    while (cursor.moveToNext()){
                        list.add(cursor.getString(cursor.getColumnIndex(COL_RESTAURANT)))
                    }
                    cursor.close()
                }
            }

        } catch (e: SQLException) {
            Log.e(TAG, "get list error:" + e.toString())
            e.printStackTrace()
        }
        return list
    }

    fun clearNormalTangjia(){
        try {
            synchronized(this){
                var buffer = StringBuilder()
                buffer.append("delete from ").append(TABLE_NORMAL_DINNER)
                storeDb!!.execSQL(buffer.toString())
            }

        }catch (e:SQLException){
            Log.e(TAG,e.toString())
            e.printStackTrace()
        }
    }

    fun clearNormalLifeRegion(){
        try {
            synchronized(this){
                var buffer = StringBuilder()
                buffer.append("delete from ").append(TABLE_NORMAL_LIFE_REGION)
                storeDb!!.execSQL(buffer.toString())
            }

        }catch (e:SQLException){
            Log.e(TAG,e.toString())
            e.printStackTrace()
        }
    }

    fun clearBig(){
        try {
            synchronized(this){
                var buffer = StringBuilder()
                buffer.append("delete from ").append(TABLE_BIG_DINNER)
                storeDb!!.execSQL(buffer.toString())
            }

        }catch (e:SQLException){
            Log.e(TAG,e.toString())
            e.printStackTrace()
        }
    }


}
