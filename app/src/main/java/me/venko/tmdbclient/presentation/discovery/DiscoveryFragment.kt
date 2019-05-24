package me.venko.tmdbclient.presentation.discovery

import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_discovery.*
import me.venko.tmdbclient.R
import me.venko.tmdbclient.presentation.common.fragment.BaseFragment
import me.venko.tmdbclient.presentation.common.recyclerview.HorizontalMarginItemDecoration

/**
 * @author Victor Kosenko
 *
 */
class DiscoveryFragment : BaseFragment() {
    override val layoutId: Int = R.layout.fragment_discovery

    private lateinit var discoveryViewModel: DiscoveryViewModel

    private var resultsAdapter: MoviesAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        discoveryViewModel = ViewModelProviders.of(this).get(DiscoveryViewModel::class.java)
        setupDiscovery()
    }

    private fun setupDiscovery() {
        with(rvPopular) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            addItemDecoration(
                HorizontalMarginItemDecoration(resources.getDimension(R.dimen.view_margin_half_regular).toInt())
            )
            adapter = MoviesAdapter(requireContext()).also { resultsAdapter = it }
        }
    }
}
