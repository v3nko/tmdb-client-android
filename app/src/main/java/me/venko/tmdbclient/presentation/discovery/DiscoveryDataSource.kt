package me.venko.tmdbclient.presentation.discovery

import androidx.paging.DataSource
import androidx.paging.PageKeyedDataSource
import me.venko.tmdbclient.core.model.discovery.Movie

/**
 * @author Victor Kosenko
 *
 */
class DiscoveryDataSource(private val fetch: (Int, onReady: (List<Movie>) -> Unit) -> Unit) :
    PageKeyedDataSource<Int, Movie>() {

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        fetch(PAGE_INITIAL) { callback.onResult(it, null, PAGE_INITIAL + 1) }

    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        fetch(params.key) { callback.onResult(it, params.key + 1) }
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {

    }

    companion object {
        private const val PAGE_INITIAL = 1
    }
}

class DiscoveryDataSourceFactory(
    private val fetch: (Int, onReady: (List<Movie>) -> Unit) -> Unit
) : DataSource.Factory<Int, Movie>() {
    override fun create(): DataSource<Int, Movie> = DiscoveryDataSource(fetch)
}
