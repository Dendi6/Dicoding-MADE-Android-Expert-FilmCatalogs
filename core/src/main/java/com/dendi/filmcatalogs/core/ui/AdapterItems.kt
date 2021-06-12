package com.dendi.filmcatalogs.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dendi.filmcatalogs.core.BuildConfig
import com.dendi.filmcatalogs.core.databinding.FilmsItemBinding
import com.dendi.filmcatalogs.core.domain.model.Film

class AdapterItems : RecyclerView.Adapter<AdapterItems.ListViewHolder>() {

    private var listData = ArrayList<Film>()
    var onItemClick: ((Film) -> Unit)? = null

    fun setData(newListData: List<Film>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val itemsMovies =
            FilmsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(itemsMovies)
    }

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ListViewHolder(private val binding: FilmsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: Film) {
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGES + "/${data.images}")
                .into(binding.imageItem)
        }
        init {
            binding.root.setOnClickListener {
                onItemClick?.invoke(listData[adapterPosition])
            }
        }
    }
}

