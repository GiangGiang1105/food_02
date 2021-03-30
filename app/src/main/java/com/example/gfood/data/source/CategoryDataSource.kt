package com.example.gfood.data.source

import com.example.gfood.data.model.Category
import com.example.gfood.data.source.remote.OnFetchDataJsonListener

interface CategoryDataSource {

    interface Remote {
        fun getCategory(listener: OnFetchDataJsonListener<MutableList<Category>>)
    }
}
