package com.readboy.whattoeat.DataRecord

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log

/**
 * Created by Ilystina on 2017/11/2.
 */
class DataBaseHelper(context: Context, val name: String, version: Int) : SQLiteOpenHelper(context, name, null, version) {

    val TAG = "DataBaseHelper"

    override fun onCreate(db: SQLiteDatabase?) {
        Log.d(TAG,"OnCreate")
        createDbTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        Log.d(TAG,"OnUpGrade")
        createDbTable(db)
    }

    private val TABLE_NORMAL_DINNER: String = "TABLE_NORMAL_DINNER"
    val TABLE_NORMAL_LIFE_REGION:String = "TABLE_NORMAL_LIFE_REGION"
    val TABLE_BIG_DINNER: String = "TABLE_BIG_DINNER"
    val COL_ID: String = "_id"
    val COL_RESTAURANT = "restaurant"
    fun createDbTable(db: SQLiteDatabase?) {
        if (db != null) {
            var buffer1 = StringBuffer()
            buffer1.append("CREATE TABLE IF NOT EXISTS")
                    .append(" " + TABLE_NORMAL_DINNER)
                    .append(" (")
                    .append(COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,")
                    .append(COL_RESTAURANT+" varchar(50))")
            db.execSQL(buffer1.toString())

            var buffer2 = StringBuffer()
            buffer2.append("CREATE TABLE IF NOT EXISTS")
                    .append(" " + TABLE_BIG_DINNER)
                    .append(" (")
                    .append(COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,")
                    .append(COL_RESTAURANT+" varchar(50))")
            db.execSQL(buffer2.toString())
            var buffer3 = StringBuffer()
            buffer3.append("CREATE TABLE IF NOT EXISTS")
                    .append(" " + TABLE_NORMAL_LIFE_REGION)
                    .append(" (")
                    .append(COL_ID+" INTEGER PRIMARY KEY AUTOINCREMENT,")
                    .append(COL_RESTAURANT+" varchar(50))")
            db.execSQL(buffer3.toString())
        }
    }

}