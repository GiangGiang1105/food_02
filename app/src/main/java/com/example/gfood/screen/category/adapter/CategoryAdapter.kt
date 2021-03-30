package com.example.gfood.screen.category.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gfood.R
import com.example.gfood.data.model.Category

class CategoryAdapter(
        private var onItemClickListener: (Category) -> Unit
) : RecyclerView.Adapter<CategoryViewHolder>() {

    private val categories = mutableListOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_layout_category, parent, false)
        return CategoryViewHolder(view, onItemClickListener)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        holder.bindViewData(categories[position])
    }

    override fun getItemCount() = categories.size

    fun updateData(listCategory: MutableList<Category>) {
        listCategory.let {
            categories.clear()
            categories.addAll(it)
            notifyDataSetChanged()
        }
    }
}
