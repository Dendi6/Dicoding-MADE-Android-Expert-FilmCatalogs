package com.dendi.filmscatalogs.favorite

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dendi.filmscatalogs.core.ui.AdapterItems
import com.dendi.filmscatalogs.databinding.ActivityFavoriteBinding
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