package com.dendi.filmscatalogs.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.dendi.filmscatalogs.R
import com.dendi.filmscatalogs.core.ui.AdapterItems
import com.dendi.filmscatalogs.core.vo.Resource
import com.dendi.filmscatalogs.home.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeTabFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModel()

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

        if (index == 0) {
            homeViewModel.getMovies().observe(viewLifecycleOwner, { listMovie ->
                if (listMovie != null) {
                    when (listMovie) {
                        is Resource.Loading -> progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            homeAdapter.setData(listMovie.data)
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        } else {
            homeViewModel.getTvShow().observe(viewLifecycleOwner, { listTvShow ->
                if (listTvShow != null) {
                    when (listTvShow) {
                        is Resource.Loading -> progressBar.visibility = View.VISIBLE
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            homeAdapter.setData(listTvShow.data)
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                            Toast.makeText(context, "Terjadi kesalahan", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            })
        }
    }
}