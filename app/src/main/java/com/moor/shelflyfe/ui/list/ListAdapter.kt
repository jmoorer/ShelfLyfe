package com.moor.shelflyfe.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemTextBinding

class ListAdapter(val items:Array<ListItem>):RecyclerView.Adapter<ListAdapter.ViewHolder>() {

    var listener:OnClickListener?=null

    interface OnClickListener{
        fun onClick(listItem: ListItem)
    }
    inner class ViewHolder(val binding:ItemTextBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(item:ListItem){
           binding.textView.text = item.value
            binding.root.setOnClickListener {
                listener?.onClick(item)
            }
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