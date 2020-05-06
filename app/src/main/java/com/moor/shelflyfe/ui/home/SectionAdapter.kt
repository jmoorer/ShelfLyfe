package com.moor.shelflyfe.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.moor.shelflyfe.databinding.ItemSectionBinding
import com.moor.shelflyfe.databinding.ItemSectionBookBinding
import com.moor.shelflyfe.load
import com.moor.shelflyfe.ui.Book
import com.moor.shelflyfe.ui.Section


class SectionAdapter(val sections:List<Section>):RecyclerView.Adapter<SectionAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemSectionBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(section: Section)= binding.apply {
            sectionTitle.text= section.title
            sectionBooks.apply {
                layoutManager=GridLayoutManager(binding.root.context,3)
                setHasFixedSize(true)
                addItemDecoration(SpacesItemDecoration(8))
                adapter= SubSectionAdapter(section.books)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemSectionBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()= sections.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sections[position])
    }
}

class SubSectionAdapter(val books: List<Book>):RecyclerView.Adapter<SubSectionAdapter.ViewHolder>(){

    class ViewHolder(val binding: ItemSectionBookBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(book: Book){
            book.imageUrl?.let { binding.bookCover.load(it) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return  ViewHolder(ItemSectionBookBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount()= 6

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
       holder.bind(books[position])
    }
}