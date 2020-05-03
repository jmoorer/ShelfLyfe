package com.moor.shelflyfe.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.api.nyt.models.SellerList
import com.moor.shelflyfe.api.nyt.models.SellerListInfo
import com.moor.shelflyfe.databinding.ItemCategoryBinding

class CategoryAdapter(val sellerLists:List<SellerListInfo>):RecyclerView.Adapter<CategoryAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemCategoryBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(sellerListInfo: SellerListInfo){
            binding.name.text= sellerListInfo.displayName
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()=sellerLists.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(sellerLists[position])
    }
}