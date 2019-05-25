package me.venko.tmdbclient.data.repo

import me.venko.tmdbclient.core.model.common.Result
import me.venko.tmdbclient.core.model.discovery.DiscoveryItems
import me.venko.tmdbclient.core.repo.MoviesRepository
import me.venko.tmdbclient.data.api.TmdbApi
import org.threeten.bp.LocalDate
import javax.inject.Inject

/**
 * @author Victor Kosenko
 *
 */
class TmdbRepository @Inject constructor(private val api: TmdbApi) : BaseApiRepository(), MoviesRepository {
    override suspend fun getPopularMovies(page: Int): Result<DiscoveryItems> =
        performApiCall { api.getPopularMoviesAsync(page).await() }

    override suspend fun getTopRatedMovies(page: Int): Result<DiscoveryItems> =
        performApiCall { api.getTopRatedMoviesAsync(page).await() }

    override suspend fun getTopRevenueMovies(page: Int): Result<DiscoveryItems> =
        performApiCall { api.getTopRevenueMoviesAsync(page).await() }

    override suspend fun getMostRecentMovies(page: Int, releasedBefore: LocalDate): Result<DiscoveryItems> =
        performApiCall { api.getMostRecentMoviesAsync(page, releasedBefore.toString()).await() }
}
