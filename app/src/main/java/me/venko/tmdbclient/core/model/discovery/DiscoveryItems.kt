package me.venko.tmdbclient.core.model.discovery


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

/**
 * @author Victor Kosenko
 *
 */
@JsonClass(generateAdapter = true)
data class DiscoveryItems(
    val page: Int,
    @Json(name = "total_results")
    val totalResults: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    val results: List<Movie>
)
