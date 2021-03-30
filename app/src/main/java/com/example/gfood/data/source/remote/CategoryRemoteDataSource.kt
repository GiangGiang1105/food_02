package com.example.gfood.data.source.remote

import com.example.gfood.data.model.Category
import com.example.gfood.data.source.CategoryDataSource
import com.example.gfood.data.source.remote.fetchjson.GetJsonFromUrl
import com.example.gfood.utils.Constant
import com.example.gfood.utils.KeyEntity

@Suppress("DEPRECATION")
class CategoryRemoteDataSource private constructor() : CategoryDataSource.Remote {
    private var baseUrl = Constant.BASE_URL + Constant.BASE_CATEGORY

    override fun getCategory(listener: OnFetchDataJsonListener<MutableList<Category>>) {
        GetJsonFromUrl(KeyEntity.CATEGORY, listener).execute(baseUrl)
    }

    companion object {
        private var instance: CategoryRemoteDataSource? = null

        fun getInstances(): CategoryRemoteDataSource {
            return instance ?: CategoryRemoteDataSource().also {
                instance = it
            }
        }
    }
}
