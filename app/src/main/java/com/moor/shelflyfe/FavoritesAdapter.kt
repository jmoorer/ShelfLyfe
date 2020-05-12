package com.moor.shelflyfe

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemFavoriteBinding
import com.moor.shelflyfe.db.Favorite

class FavoritesAdapter(val favorites: List<Favorite>):RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {


    inner  class  ViewHolder(val binding:ItemFavoriteBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(favorite: Favorite)=binding.apply {
            cover.load(favorite.imageUrl)
            title.text= favorite.title
            author.text= "By ${favorite.author}"
            ratingBar.numStars=5
            ratingBar.rating = favorite.rating

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
      return  ViewHolder(ItemFavoriteBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return  favorites.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(favorites[position])
    }
}