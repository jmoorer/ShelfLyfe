package com.moor.shelflyfe.ui.explore

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemCategoryBinding
import com.moor.shelflyfe.ui.Category


class CategoryAdapter(val genres:List<Genre>): RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    interface OnGenreClickListener{
        fun onGenreClick(genre: Genre)
    }

    var listener :OnGenreClickListener?=null

    inner class ViewHolder(val binding: ItemCategoryBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(genre: Genre)= binding.apply {
            name.text= HtmlCompat.fromHtml(genre.name, 0)
            binding.root.setOnClickListener {
                listener?.onGenreClick(genre)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount()= genres.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres[position])
    }
}
