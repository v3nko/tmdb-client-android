package me.venko.tmdbclient.core.repo

import me.venko.tmdbclient.core.model.common.Result
import me.venko.tmdbclient.core.model.discovery.DiscoveryItems
import org.threeten.bp.LocalDate

/**
 * @author Victor Kosenko
 *
 */
interface MoviesRepository {
    suspend fun getPopularMovies(page: Int): Result<DiscoveryItems>
    suspend fun getTopRatedMovies(page: Int): Result<DiscoveryItems>
    suspend fun getTopRevenueMovies(page: Int): Result<DiscoveryItems>
    suspend fun getMostRecentMovies(page: Int, releasedBefore: LocalDate): Result<DiscoveryItems>
}
