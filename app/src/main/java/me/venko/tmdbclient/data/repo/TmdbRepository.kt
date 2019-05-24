package me.venko.tmdbclient.data.repo

import me.venko.tmdbclient.core.model.common.Result
import me.venko.tmdbclient.core.model.discovery.DiscoveryItems
import me.venko.tmdbclient.core.repo.MoviesRepository
import me.venko.tmdbclient.data.api.TmdbApi
import javax.inject.Inject

/**
 * @author Victor Kosenko
 *
 */
class TmdbRepository @Inject constructor(private val api: TmdbApi) : BaseApiRepository(), MoviesRepository {
    override suspend fun getPopularMovies(page: Int): Result<DiscoveryItems> {
        return performApiCall { api.getPopularMoviesAsync(page).await() }
    }
}
