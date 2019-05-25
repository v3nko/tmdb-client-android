package me.venko.tmdbclient.presentation.discovery

import android.os.Bundle
import android.view.View
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_discovery.*
import me.venko.tmdbclient.R
import me.venko.tmdbclient.core.model.discovery.Movie
import me.venko.tmdbclient.presentation.common.fragment.BaseFragment
import me.venko.tmdbclient.presentation.common.recyclerview.HorizontalMarginItemDecoration

/**
 * @author Victor Kosenko
 *
 */
class DiscoveryFragment : BaseFragment() {

    companion object {
        fun newInstance() = DiscoveryFragment()
    }

    override val layoutId: Int = R.layout.fragment_discovery

    private lateinit var discoveryViewModel: DiscoveryViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discoveryViewModel = ViewModelProviders.of(this).get(DiscoveryViewModel::class.java)
        setupDiscovery()
    }

    private fun setupDiscovery() {
        discoveryViewModel.loadDiscovery()

        setupDiscoverySection(rvPopular, discoveryViewModel.popularMovies)
        setupDiscoverySection(rvTopRated, discoveryViewModel.topRatedMovies)
        setupDiscoverySection(rvTopRevenue, discoveryViewModel.topRevenueMovies)
        setupDiscoverySection(rvMostRecent, discoveryViewModel.mostRecentMovies)
    }

    private fun setupDiscoverySection(recyclerView: RecyclerView, data: LiveData<PagedList<Movie>>): MoviesAdapter {
        val moviesAdapter = MoviesAdapter(requireContext(), onItemClick = {
            discoveryViewModel.displayMovieDetails(it)
        })
        with(recyclerView) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(
                HorizontalMarginItemDecoration(resources.getDimension(R.dimen.view_margin_half_regular).toInt())
            )
            adapter = moviesAdapter
        }
        data.observe(this, Observer {
            moviesAdapter.submitList(it)
        })
        return moviesAdapter
    }
}
