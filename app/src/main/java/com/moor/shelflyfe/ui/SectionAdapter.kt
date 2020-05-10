package com.moor.shelflyfe.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

import com.moor.shelflyfe.databinding.ItemSectionBinding
import com.moor.shelflyfe.databinding.ItemSectionBookBinding
import com.moor.shelflyfe.load


class SectionAdapter(val sections:List<Section>):RecyclerView.Adapter<SectionAdapter.ViewHolder>(){


    var listener: OnBookClickListner?=null

    inner class ViewHolder(val binding: ItemSectionBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(section: Section)= binding.apply {
            sectionTitle.text= section.title
            sectionBooks.apply {
                layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
                setHasFixedSize(true)
                addItemDecoration(
                    SpacesItemDecoration(
                        8
                    )
                )
                adapter= SubSectionAdapter(section.books).apply {
                    listener=this@SectionAdapter.listener
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemSectionBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount()= sections.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(sections[position])
    }




    class SubSectionAdapter(val books: List<Book>):RecyclerView.Adapter<SubSectionAdapter.ViewHolder>(){

        var listener: OnBookClickListner?=null
        inner class ViewHolder(val binding: ItemSectionBookBinding):RecyclerView.ViewHolder(binding.root){
            fun bind(book: Book){
                book.imageUrl?.let { binding.bookCover.load(it) }
                binding.root.setOnClickListener {
                    listener?.onClick(book)
                }
            }
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            return ViewHolder(
                ItemSectionBookBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            )
        }

        override fun getItemCount() = books.size

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.bind(books[position])
        }
    }
}

