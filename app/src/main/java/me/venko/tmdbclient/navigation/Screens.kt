package me.venko.tmdbclient.navigation

import androidx.fragment.app.Fragment
import me.venko.tmdbclient.core.model.discovery.Movie
import me.venko.tmdbclient.presentation.details.MovieDetailsFragment
import me.venko.tmdbclient.presentation.details.MovieDetailsPlaceholderFragment
import me.venko.tmdbclient.presentation.discovery.DiscoveryFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * @author Victor Kosenko
 *
 */
interface MasterScreen

interface DetailsScreen

class DiscoveryScreen : SupportAppScreen(), MasterScreen {
    override fun getFragment(): Fragment = DiscoveryFragment.newInstance()
}

class MovieDetailsScreen(private val movie: Movie) : SupportAppScreen(), DetailsScreen {
    override fun getFragment(): Fragment = MovieDetailsFragment.newInstance(movie)
}

class MovieDetailsPlaceholderScreen : SupportAppScreen(), DetailsScreen {
    override fun getFragment(): Fragment {
        return MovieDetailsPlaceholderFragment.newInstance()
    }
}
