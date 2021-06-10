package com.dendi.filmscatalogs.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.core.ui.AdapterItems
import com.dendi.filmscatalogs.core.ui.ViewModelFactory
import com.dendi.filmscatalogs.core.vo.Status
import com.dendi.filmscatalogs.home.HomeViewModel

class HomeTabFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel

    companion object {
        private const val ARG_SECTION_NUMBER = "section_number"

        @JvmStatic
        fun newInstance(index: Int) = HomeTabFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_SECTION_NUMBER, index)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home_tab, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val rvItems: RecyclerView = view.findViewById(R.id.rv_items)
        val progressBar: ProgressBar = view.findViewById(R.id.progress_bar)
        val index = arguments?.getInt(ARG_SECTION_NUMBER, 0)
        val homeAdapter = AdapterItems()

        rvItems.layoutManager = GridLayoutManager(context, 3)
        rvItems.setHasFixedSize(true)
        rvItems.adapter = homeAdapter

        val factory = ViewModelFactory.getInstance(requireActivity())
        homeViewModel = ViewModelProvider(this, factory)[HomeViewModel::class.java]

        if (index == 0) {
            homeViewModel.getMovies().observe(this, { listMovie ->
                if (listMovie != null) {
                    when (listMovie.status) {
                        Status.LOADING -> progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progressBar.visibility = View.GONE
                            homeAdapter.setData(listMovie.data)
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        } else {
            homeViewModel.getTvShow().observe(this, { listTvShow ->
                if (listTvShow != null) {
                    when (listTvShow.status) {
                        Status.LOADING -> progressBar.visibility = View.VISIBLE
                        Status.SUCCESS -> {
                            progressBar.visibility = View.GONE
                            homeAdapter.setData(listTvShow.data)
                        }
                        Status.ERROR -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }
}