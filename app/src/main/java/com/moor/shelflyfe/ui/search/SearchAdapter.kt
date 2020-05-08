package com.moor.shelflyfe.ui.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemSearchResultBinding
import com.moor.shelflyfe.load
import com.moor.shelflyfe.ui.Book

class SearchAdapter(var books: List<Book>): RecyclerView.Adapter<SearchAdapter.ViewHolder>(){


    interface  OnItemClickListner{
        fun onClick(book:Book)
    }

    var listener :OnItemClickListner?=null

    inner class ViewHolder(val binding: ItemSearchResultBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(book: Book ) = binding.apply {
            title.text= book.title
            author.text= book.author
            book.imageUrl?.let { binding.cover.load(it) }
            root.setOnClickListener {
                listener?.onClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSearchResultBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount()= books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(books[position])
    }
}
