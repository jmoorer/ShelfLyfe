package com.moor.shelflyfe.ui.explore

import android.text.Html
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemFeaturedBinding
import com.moor.shelflyfe.db.Trending
import com.moor.shelflyfe.load
import com.moor.shelflyfe.preloadImage
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.OnBookClickListner
import com.moor.shelflyfe.ui.OnItemClickListener

class FeaturedAdapter(val books: List<Trending>): RecyclerView.Adapter<FeaturedAdapter.ViewHolder>() {

    var listener: OnItemClickListener<Trending>?=null

    init {
        books.forEach { book->
            book.imageUrl?.let { preloadImage(it) }
        }
    }
    inner class ViewHolder(val binding: ItemFeaturedBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(book :Trending)= binding.apply {
            bookTitle.text= Html.fromHtml(book.title)
            book.imageUrl?.let {
                coverImage.load(it){ exception, palette ->
                    palette?.vibrantSwatch?.let { swatch ->
                        root.setBackgroundColor(swatch.rgb)
                        bookTitle.setTextColor(swatch.titleTextColor)
                        authorName.setTextColor(swatch.bodyTextColor)
                    }
                }
            }
            authorName.text = "By ${Html.fromHtml(book.author)}"
            root.setOnClickListener {
               listener?.onClick(book)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemFeaturedBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount()= books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(books[position])
    }
}