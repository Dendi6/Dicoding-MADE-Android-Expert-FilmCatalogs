package com.dendi.filmscatalogs.detail

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.dendi.filmscatalogs.BuildConfig
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.core.data.source.local.entity.ListEntity
import com.dendi.filmscatalogs.core.domain.model.Film
import com.dendi.filmscatalogs.core.ui.ViewModelFactory
import com.dendi.filmscatalogs.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding
    private lateinit var detailActivityViewModel: DetailActivityViewModel

    private var menu: Menu? = null

    companion object {
        const val EXTRA_DATA = "extra_data"
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val factory = ViewModelFactory.getInstance(this)
        val film = intent.getParcelableExtra<Film>(EXTRA_DATA) as Film

        supportActionBar?.apply {
            elevation = 0f
            setDisplayHomeAsUpEnabled(true)
            setActionBarTitle(film.title)
        }

        detailActivityViewModel =
            ViewModelProvider(this, factory)[DetailActivityViewModel::class.java]
        detailActivityViewModel.setSelectedFilm(film.id)

        view(film)
    }

    private fun setActionBarTitle(title: String) {
        if (supportActionBar != null) {
            this.title = title
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.detail_menu, menu)

        this.menu = menu
        val films = intent.getParcelableExtra<Film>(EXTRA_DATA) as Film
        setBookmarkState(films.favorited)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        setMode(item.itemId)
        return super.onOptionsItemSelected(item)
    }

    private fun setMode(selectedMode: Int) {
        when (selectedMode) {
            R.id.share -> {
                val films = intent.getParcelableExtra<Film>(EXTRA_DATA) as Film
                share(films)
            }
            R.id.action_bookmark -> {
                val films = intent.getParcelableExtra<Film>(EXTRA_DATA) as Film
                val newState = !films.favorited

                detailActivityViewModel.setFavorite(films, newState)
                message(newState)
                setBookmarkState(newState)
            }
        }
    }

    private fun view(data: Film) {
        Glide.with(this)
            .load(BuildConfig.IMAGES + "/${data.poster}")
            .into(binding.imagesDetail)

        binding.titleDetail.text = data.title
        binding.overview.text = data.overview
    }

    private fun setBookmarkState(state: Boolean) {
        if (menu == null) return
        val menuItem = menu?.findItem(R.id.action_bookmark)
        if (state) {
            menuItem?.icon = ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_24)
        } else {
            menuItem?.icon =
                ContextCompat.getDrawable(this, R.drawable.ic_baseline_bookmark_border_24)
        }
    }

    private fun message(state: Boolean) {
        if (state) {
            Toast.makeText(this, "Favorited", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "deleted", Toast.LENGTH_SHORT).show()
        }
    }

    private fun share(listEntity: Film) {
        val title = listEntity.title
        val overview = listEntity.overview
        val textShare = getString(R.string.text_share, title, overview)
        val intent = Intent(Intent.ACTION_SEND)
        intent.putExtra(Intent.EXTRA_TEXT, textShare)
        intent.type = "text/plain"

        startActivity(Intent.createChooser(intent, "Share using .."))
    }
}