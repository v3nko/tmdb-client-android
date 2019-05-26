package me.venko.tmdbclient.presentation.details

import me.venko.tmdbclient.R
import me.venko.tmdbclient.presentation.common.fragment.BaseFragment

/**
 * @author Victor Kosenko
 *
 */
class MovieDetailsPlaceholderFragment : BaseFragment() {
    companion object {
        fun newInstance() = MovieDetailsPlaceholderFragment()
    }

    override val layoutId: Int = R.layout.fragment_movie_details_placeholder
}
