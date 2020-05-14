package com.moor.shelflyfe

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import com.moor.shelflyfe.databinding.ItemFavoriteBinding
import com.moor.shelflyfe.db.Download


class DownloadsAdapter(val downloads: List<Download>):RecyclerView.Adapter<DownloadsAdapter.ViewHolder>() {


    interface OnDownloadClickListener{
        fun onClick(download: Download)
        fun onMenuClick(download: Download,menuItem: MenuItem)
    }
    var listener:OnDownloadClickListener?=null


    inner  class  ViewHolder(val binding:ItemFavoriteBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(download:Download)=binding.apply {
            val bmp = BitmapFactory.decodeByteArray(download.image, 0, download.image?.size?:0)
            cover.setImageBitmap(bmp)
            title.text= download.title
            author.text= "By ${download.author}"
            ratingBar.numStars=5

            root.setOnClickListener {
                listener?.onClick(download)
            }
            menuButton.setOnClickListener {
                PopupMenu(binding.root.context,it).apply {
                    inflate(R.menu.fav_menu)
                    setOnMenuItemClickListener { item ->
                        listener?.onMenuClick(download,item)
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
        return  downloads.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(downloads[position])
    }
}