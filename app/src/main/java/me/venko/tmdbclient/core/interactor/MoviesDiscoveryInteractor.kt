package me.venko.tmdbclient.core.interactor

import me.venko.tmdbclient.core.model.common.Result
import me.venko.tmdbclient.core.model.discovery.DiscoveryItems
import me.venko.tmdbclient.core.repo.MoviesRepository
import org.threeten.bp.LocalDate

/**
 * @author Victor Kosenko
 *
 */
class MoviesDiscoveryInteractor(private val repository: MoviesRepository) {
    suspend fun getPopularMovies(page: Int): Result<DiscoveryItems> = repository.getPopularMovies(page)
    suspend fun getTopRatedMovies(page: Int): Result<DiscoveryItems> = repository.getTopRatedMovies(page)
    suspend fun getTopRevenueMovies(page: Int): Result<DiscoveryItems> = repository.getTopRevenueMovies(page)
    suspend fun getMostRecentMovies(page: Int): Result<DiscoveryItems> =
        repository.getMostRecentMovies(page, LocalDate.now())
}
