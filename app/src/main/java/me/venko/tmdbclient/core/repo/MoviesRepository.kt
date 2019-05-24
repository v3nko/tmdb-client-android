package me.venko.tmdbclient.core.repo

import me.venko.tmdbclient.core.model.common.Result
import me.venko.tmdbclient.core.model.discovery.DiscoveryItems

/**
 * @author Victor Kosenko
 *
 */
interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): Result<DiscoveryItems>
}
