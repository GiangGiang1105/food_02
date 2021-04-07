package com.example.gfood.data.source.repository

import com.example.gfood.data.model.Meal
import com.example.gfood.data.source.MealFavouriteDataSource
import com.example.gfood.data.source.local.OnFetchDataLocalListener

class MealFavouriteRepository(
    private val local: MealFavouriteDataSource.Local
) {
    fun save(
        entity: Meal,
        listener: OnFetchDataLocalListener<Long>
    ) {
        local.save(entity, listener)
    }

    fun getListMealFavourite(
        listener: OnFetchDataLocalListener<MutableList<Meal>>
    ) {
        local.getListMealFavourite(listener)
    }

    fun delete(
        idEntity: String?,
        listener: OnFetchDataLocalListener<Int>
    ) {
        local.delete(idEntity, listener)
    }

    fun getMeal(
        idEntity: String?,
        listener: OnFetchDataLocalListener<Boolean>
    ) {
        local.getMeal(idEntity, listener)
    }

    companion object {
        private var instance: MealFavouriteRepository? = null

        fun getInstance(
            local: MealFavouriteDataSource.Local
        ): MealFavouriteRepository {
            return instance ?: MealFavouriteRepository(local).also {
                instance = it
            }
        }
    }
}
