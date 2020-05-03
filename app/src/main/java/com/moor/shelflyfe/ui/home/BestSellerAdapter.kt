package com.moor.shelflyfe.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.api.nyt.models.Book
import com.moor.shelflyfe.databinding.ItemBestSellerBinding
import com.moor.shelflyfe.extractPallete
import com.moor.shelflyfe.load
import com.moor.shelflyfe.preloadImage
import java.lang.Exception

class BestSellerAdapter(val books: List<Book>): RecyclerView.Adapter<BestSellerAdapter.ViewHolder>() {

    init {
        books.forEach { book->
            book.bookImage?.let { preloadImage(it) }
        }
    }
    class ViewHolder(val binding: ItemBestSellerBinding):RecyclerView.ViewHolder(binding.root){

        fun bind(book :Book)= binding.apply {
            bookTitle.text=book.title
            book.bookImage?.let {
                coverImage.load(it)
                extractPallete(it){ e,palette->
                      palette?.vibrantSwatch?.let { swatch ->
                          root.setBackgroundColor(swatch.rgb)
                          bookTitle.setTextColor(swatch.titleTextColor)
                          authorName.setTextColor(swatch.bodyTextColor)
                      }

                }
            }
            authorName.text = "By ${book.author}"
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemBestSellerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun getItemCount()= books.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(books[position])
    }
}