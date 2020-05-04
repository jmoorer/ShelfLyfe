package com.moor.shelflyfe.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemCategoryBinding
import com.moor.shelflyfe.databinding.ItemSectionBinding


class CategoryAdapter(val categories:List<Category>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(section: Section)= binding.apply {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()= categories.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       // holder.bind(sections[position])
    }
}
