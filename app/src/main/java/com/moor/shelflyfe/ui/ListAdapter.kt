package com.moor.shelflyfe.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemTextBinding

class ListAdapter<T>(var title:String?=null,val items:List<T> ,val selector:(T)->String):RecyclerView.Adapter<ListAdapter<T>.ViewHolder>() {

    inner class ViewHolder(val binding:ItemTextBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:T){
           binding.textView.text= selector(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemTextBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()= items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(items[position])
    }


}