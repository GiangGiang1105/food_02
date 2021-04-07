package com.example.gfood.data.source.local.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.gfood.data.source.local.database.table.MealFavouriteTable

class DatabaseHelper private constructor(context: Context?) :
    SQLiteOpenHelper(context, NAME_DATABASE, null, VERSION_DATABASE) {

    override fun onCreate(db: SQLiteDatabase?) {
        val sqlCreateMealTable = "CREATE TABLE " +
                "${MealFavouriteTable.TABLE_NAME}(" +
                "${MealFavouriteTable.COLUMN_NAME} TEXT," +
                "${MealFavouriteTable.COLUMN_ID_MEAL} TEXT," +
                "${MealFavouriteTable.COLUMN_IMAGE} TEXT)"
        db?.execSQL(sqlCreateMealTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) = Unit

    companion object {
        const val NAME_DATABASE = "MealFavourite.db"
        const val VERSION_DATABASE = 1

        @Volatile
        private var instance: DatabaseHelper? = null

        fun getInstance(context: Context?): DatabaseHelper =
            instance ?: DatabaseHelper(context).also {
                instance = it
            }
    }
}
