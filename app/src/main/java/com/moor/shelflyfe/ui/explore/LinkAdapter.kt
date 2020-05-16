package com.moor.shelflyfe.ui.explore

import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemLinkBinding
import com.moor.shelflyfe.ui.list.ListItem

class LinkAdapter(val items:List<ListItem>):RecyclerView.Adapter<LinkAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: ItemLinkBinding):RecyclerView.ViewHolder(binding.root){

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemLinkBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()= items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var item=items[position]
        holder.binding.link.apply{
            isClickable = true
            movementMethod=LinkMovementMethod.getInstance()
            text = Html.fromHtml("<a href='${item.key}'>${item.value}</a>")
        }
            //holder.binding.link.movementMethod=LinkMovementMethod.getInstance()
    }


}