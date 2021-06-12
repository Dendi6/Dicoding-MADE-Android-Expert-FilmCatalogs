package com.dendi.filmscatalogs.favorite

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dendi.filmcatalogs.core.ui.AdapterItems
import com.dendi.filmscatalogs.databinding.ActivityFavoriteBinding
import com.dendi.filmscatalogs.detail.DetailActivity
import org.koin.android.viewmodel.ext.android.viewModel

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()
    private lateinit var adapter: AdapterItems

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.apply {
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
            title = "Favorite"
        }

        adapter = AdapterItems()
        binding.rvFavorited.layoutManager = GridLayoutManager(this, 3)
        binding.rvFavorited.setHasFixedSize(true)
        binding.rvFavorited.adapter = adapter

        adapter.onItemClick = { selectedData ->
            val intent = Intent(this, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, selectedData)
            startActivity(intent)
        }

        favoriteViewModel.getFavorite().observe(this, { items ->
            if (items.isEmpty()) {
                adapter.setData(items)
                binding.emptyImages.visibility = View.VISIBLE
            } else {
                binding.emptyImages.visibility = View.GONE
                adapter.setData(items)
            }
        })
    }
}