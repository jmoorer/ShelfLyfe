package com.moor.shelflyfe

import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemFavoriteBinding
import com.moor.shelflyfe.db.Favorite

class FavoritesAdapter(val favorites: List<Favorite>):RecyclerView.Adapter<FavoritesAdapter.ViewHolder>() {


    interface OnFavoriteClickListener{
        fun onClick(favorite: Favorite)
        fun onMenuClick(favorite: Favorite,menuItem: MenuItem)
    }
    var listener:OnFavoriteClickListener?=null
    inner  class  ViewHolder(val binding:ItemFavoriteBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(favorite: Favorite)=binding.apply {
            cover.load(favorite.imageUrl)
            title.text= favorite.title
            author.text= "By ${favorite.author}"
            ratingBar.numStars=5
            ratingBar.rating = favorite.rating
            root.setOnClickListener {
                listener?.onClick(favorite)
            }
            menuButton.setOnClickListener {
                PopupMenu(binding.root.context,it).apply {
                    inflate(R.menu.fav_menu)
                    setOnMenuItemClickListener { item ->
                        listener?.onMenuClick(favorite,item)
                        return@setOnMenuItemClickListener false
                    }
                }.show()

            }

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