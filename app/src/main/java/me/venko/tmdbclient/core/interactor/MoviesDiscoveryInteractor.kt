package me.venko.tmdbclient.core.interactor

import me.venko.tmdbclient.core.model.common.Result
import me.venko.tmdbclient.core.model.discovery.DiscoveryItems
import me.venko.tmdbclient.core.repo.MoviesRepository

/**
 * @author Victor Kosenko
 *
 */
class MoviesDiscoveryInteractor(private val repository: MoviesRepository) {
    suspend fun getPopularMovies(page: Int): Result<DiscoveryItems> = repository.getPopularMovies(page)
}
