package me.venko.tmdbclient.presentation.details

import me.venko.tmdbclient.R
import me.venko.tmdbclient.presentation.common.fragment.BaseFragment

/**
 * @author Victor Kosenko
 *
 */
class MovieDetailsPlaceholderFragment : BaseFragment() {

    override val layoutId: Int = R.layout.fragment_movie_details_placeholder

    companion object {
        fun newInstance() = MovieDetailsPlaceholderFragment()
    }
}
