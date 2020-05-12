package com.moor.shelflyfe.ui.bookdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemCategoryBinding
import com.moor.shelflyfe.databinding.ItemTagBinding
import com.moor.shelflyfe.ui.explore.Genre
import com.moor.shelflyfe.ui.list.ListItem

class TagAdapter(private val tags:List<ListItem>):RecyclerView.Adapter<TagAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemTagBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ListItem)= binding.apply {
            tag.text= HtmlCompat.fromHtml(item.value, 0)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTagBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount()= tags.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tags[position])
    }
}