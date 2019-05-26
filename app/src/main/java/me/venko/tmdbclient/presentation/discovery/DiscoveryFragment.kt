package me.venko.tmdbclient.presentation.discovery

import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.core.widget.ContentLoadingProgressBar
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.section_discovery_most_recent.*
import kotlinx.android.synthetic.main.section_discovery_popular.*
import kotlinx.android.synthetic.main.section_discovery_top_rated.*
import kotlinx.android.synthetic.main.section_discovery_top_revenue.*
import me.venko.tmdbclient.R
import me.venko.tmdbclient.core.model.discovery.Movie
import me.venko.tmdbclient.presentation.common.fragment.BaseFragment
import me.venko.tmdbclient.presentation.common.recyclerview.HorizontalMarginItemDecoration
import me.venko.tmdbclient.presentation.common.view.ViewContentState

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

        setupDiscoverySection(
            rvPopular,
            pbPopularLoading,
            tvPopularStatus,
            discoveryViewModel.popularContentState,
            discoveryViewModel.popularMovies
        )
        setupDiscoverySection(
            rvTopRated,
            pbTopRatedLoading,
            tvTopRatedStatus,
            discoveryViewModel.topRatedContentState,
            discoveryViewModel.topRatedMovies
        )
        setupDiscoverySection(
            rvTopRevenue,
            pbTopRevenueLoading,
            tvTopRevenueStatus,
            discoveryViewModel.topRevenueContentState,
            discoveryViewModel.topRevenueMovies
        )
        setupDiscoverySection(
            rvMostRecent,
            pbMostRecentLoading,
            tvMostRecentStatus,
            discoveryViewModel.mostRecentContentState,
            discoveryViewModel.mostRecentMovies
        )
    }

    private fun setupDiscoverySection(
        recyclerView: RecyclerView,
        progressBar: ContentLoadingProgressBar,
        tvStatus: TextView,
        state: LiveData<ViewContentState>,
        data: LiveData<PagedList<Movie>>
    ): MoviesAdapter {
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
        state.observe(this, Observer {
            recyclerView.visibility = if (it == ViewContentState.READY) View.VISIBLE else View.GONE
            if (it == ViewContentState.LOADING) {
                progressBar.show()
            } else {
                progressBar.hide()
            }
            with(tvStatus) {
                when (it) {
                    ViewContentState.EMPTY -> {
                        visibility = View.VISIBLE
                        text = getString(R.string.tv_discovery_section_content_empty)
                    }
                    ViewContentState.ERROR -> {
                        visibility = View.VISIBLE
                        text = getString(R.string.tv_discovery_section_content_error)
                    }
                    else -> visibility = View.GONE
                }
            }
        })
        data.observe(this, Observer {
            moviesAdapter.submitList(it)
        })
        return moviesAdapter
    }
}
