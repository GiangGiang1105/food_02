package com.example.gfood.screen.category

import com.example.gfood.data.model.Category
import com.example.gfood.utils.BasePresenter
import java.lang.Exception

interface CategoryContract {

    interface View {
        fun onGetCategorySuccess(categories: MutableList<Category>)
        fun onError(exception: Exception?)
    }

    interface Presenter : BasePresenter<View> {
        fun getCategory()
    }
}
