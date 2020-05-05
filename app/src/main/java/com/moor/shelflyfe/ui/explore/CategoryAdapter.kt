package com.moor.shelflyfe.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemCategoryBinding
import com.moor.shelflyfe.ui.home.Category
import com.moor.shelflyfe.ui.home.Section


class CategoryAdapter(val categories:List<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(category: Category)= binding.apply {
            name.text= category.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount()= categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(categories[position])
    }
}
