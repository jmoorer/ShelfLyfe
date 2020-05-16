package com.moor.shelflyfe.ui.bookdetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.moor.shelflyfe.databinding.ItemCategoryBinding
import com.moor.shelflyfe.databinding.ItemTagBinding
import com.moor.shelflyfe.databinding.ItemTagSectionBinding
import com.moor.shelflyfe.ui.OnItemClickListener
import com.moor.shelflyfe.ui.SpacesItemDecoration

import com.moor.shelflyfe.ui.list.ListItem


class TagSection(var title:String, val tags:List<ListItem>)

class TagSectionAdapter(private val sections:List<TagSection>):RecyclerView.Adapter<TagSectionAdapter.ViewHolder>(){

    var listener: OnItemClickListener<ListItem>?=null
    inner class ViewHolder(val binding: ItemTagSectionBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(section: TagSection)= binding.apply {
            label.text= HtmlCompat.fromHtml(section.title, 0)
            tags.apply {
                adapter= TagAdapter(section.tags).apply {
                    listener= this@TagSectionAdapter.listener
                }
                layoutManager= StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.HORIZONTAL)
                addItemDecoration(SpacesItemDecoration(8))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemTagSectionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount()= sections.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sections[position])
    }
}
class TagAdapter(private val tags:List<ListItem>):RecyclerView.Adapter<TagAdapter.ViewHolder>(){
    var listener: OnItemClickListener<ListItem>?=null
    inner class ViewHolder(val binding: ItemTagBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(item: ListItem)= binding.apply {
            tag.text= HtmlCompat.fromHtml(item.value, 0)
            root.setOnClickListener {
                listener?.onClick(item)
            }
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