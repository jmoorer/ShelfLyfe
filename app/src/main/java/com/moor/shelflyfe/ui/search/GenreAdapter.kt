package com.moor.shelflyfe.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemGenreBinding
import com.moor.shelflyfe.db.Genre


class GenreAdapter(val genres:List<Genre>):RecyclerView.Adapter<GenreAdapter.ViewHolder>(){

    interface OnGenreClickListener{
        fun onGenreClick(genre: Genre)
    }

    var listener :OnGenreClickListener?=null
    inner class ViewHolder(val binding: ItemGenreBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(genre: Genre)= binding.apply {
            genreName.text=genre.name
            binding.root.setOnClickListener {
                listener?.onGenreClick(genre)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemGenreBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount()= genres.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(genres[position])
    }
}