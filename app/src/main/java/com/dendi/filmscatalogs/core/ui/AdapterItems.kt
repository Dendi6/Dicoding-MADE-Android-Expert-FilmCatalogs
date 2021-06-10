package com.dendi.filmscatalogs.core.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dendi.filmscatalogs.BuildConfig
import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.databinding.FilmsItemBinding
import com.dendi.filmscatalogs.detail.DetailActivity

class AdapterItems : RecyclerView.Adapter<AdapterItems.ContentViewHolder>() {

    private var listData = ArrayList<ListEntity>()
    fun setData(newListData: List<ListEntity>?) {
        if (newListData == null) return
        listData.clear()
        listData.addAll(newListData)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContentViewHolder {
        val itemsMovies =
            FilmsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ContentViewHolder(itemsMovies)
    }

    override fun onBindViewHolder(holder: ContentViewHolder, position: Int) {
        holder.bind(listData[position])
    }

    override fun getItemCount(): Int = listData.size

    inner class ContentViewHolder(private val binding: FilmsItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(data: ListEntity) {
            Glide.with(itemView.context)
                .load(BuildConfig.IMAGES + "/${data.images}")
                .into(binding.imageItem)

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_DATA, data)
                itemView.context.startActivity(intent)
            }
        }
    }
}

