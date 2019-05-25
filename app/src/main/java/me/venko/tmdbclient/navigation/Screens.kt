package me.venko.tmdbclient.navigation

import androidx.fragment.app.Fragment
import me.venko.tmdbclient.core.model.discovery.Movie
import me.venko.tmdbclient.presentation.details.MovieDetailsFragment
import me.venko.tmdbclient.presentation.discovery.DiscoveryFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * @author Victor Kosenko
 *
 */
class DiscoveryScreen : SupportAppScreen() {
    override fun getFragment(): Fragment = DiscoveryFragment.newInstance()
}

class MovieDetailsScreen(private val movie: Movie) : SupportAppScreen() {
    override fun getFragment(): Fragment = MovieDetailsFragment.newInstance(movie)
}
