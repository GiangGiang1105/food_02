package com.example.gfood.data.source.local

import android.content.Context
import com.example.gfood.data.model.Meal
import com.example.gfood.data.source.MealFavouriteDataSource
import com.example.gfood.data.source.local.database.DatabaseHelper
import com.example.gfood.data.source.local.database.dao.MealFavouriteDaoImpl

class MealFavouriteLocalDataSource private constructor(
    private val mealFavouriteDaoImpl: MealFavouriteDaoImpl
) : MealFavouriteDataSource.Local {

    override fun save(entity: Meal, listener: OnFetchDataLocalListener<Long>) {
        mealFavouriteDaoImpl.save(entity, listener)
    }

    override fun getListMealFavourite(listener: OnFetchDataLocalListener<MutableList<Meal>>) {
        mealFavouriteDaoImpl.getListMealFavourite(listener)
    }

    override fun delete(idEntity: String?, listener: OnFetchDataLocalListener<Int>) {
        mealFavouriteDaoImpl.delete(idEntity, listener)
    }

    override fun getMeal(idEntity: String?, listener: OnFetchDataLocalListener<Boolean>) {
        mealFavouriteDaoImpl.getMeal(idEntity, listener)
    }

    companion object {
        private var instance: MealFavouriteLocalDataSource? = null

        fun getInstance(context: Context?): MealFavouriteDataSource.Local =
            instance ?: MealFavouriteLocalDataSource(
                MealFavouriteDaoImpl.getInstance(
                    DatabaseHelper.getInstance(context)
                )
            ).also {
                instance = it
            }
    }
}
