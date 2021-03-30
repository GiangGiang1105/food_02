package com.example.gfood.screen.category.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gfood.data.model.Category
import com.example.gfood.utils.loadImage
import kotlinx.android.synthetic.main.item_layout_category.view.textViewCategory
import kotlinx.android.synthetic.main.item_layout_category.view.imageCategory

class CategoryViewHolder(
        itemView: View,
        private var itemListener: (Category) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bindViewData(category: Category) {
        itemView.apply {
            textViewCategory.text = category.name
            imageCategory.loadImage(
                    category.image.toString()
            )
            setOnClickListener {
                itemListener(category)
            }
        }
    }
}
