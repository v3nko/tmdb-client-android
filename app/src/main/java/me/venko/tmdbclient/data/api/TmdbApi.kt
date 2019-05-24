package me.venko.tmdbclient.data.api

import kotlinx.coroutines.Deferred
import me.venko.tmdbclient.core.model.discovery.DiscoveryItems
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author Victor Kosenko
 *
 */
interface TmdbApi {
    @GET("discover/movie?sort_by=popularity.desc")
    fun getPopularMoviesAsync(@Query("page") page: Int): Deferred<Response<DiscoveryItems>>
}
