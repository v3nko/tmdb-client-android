package me.venko.tmdbclient.presentation.discovery

import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import me.venko.tmdbclient.core.interactor.MoviesDiscoveryInteractor
import me.venko.tmdbclient.core.model.common.Result
import me.venko.tmdbclient.core.model.discovery.DiscoveryItems
import me.venko.tmdbclient.core.model.discovery.Movie
import me.venko.tmdbclient.di.Injector
import me.venko.tmdbclient.navigation.MovieDetailsScreen
import me.venko.tmdbclient.presentation.common.viewmodel.BaseViewModel
import me.venko.tmdbclient.utils.loge
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Victor Kosenko
 *
 */
class DiscoveryViewModel : BaseViewModel() {

    @Inject
    lateinit var discoveryInteractor: MoviesDiscoveryInteractor

    @Inject
    lateinit var router: Router

    lateinit var popularMovies: LiveData<PagedList<Movie>>
    lateinit var topRatedMovies: LiveData<PagedList<Movie>>
    lateinit var topRevenueMovies: LiveData<PagedList<Movie>>
    lateinit var mostRecentMovies: LiveData<PagedList<Movie>>

    init {
        Injector.appComponent().inject(this)
    }

    fun loadDiscovery() {
        popularMovies = initPagedLiveData(::getPopularMovies)
        topRatedMovies = initPagedLiveData(::getTopRatedMovies)
        topRevenueMovies = initPagedLiveData(::getTopRevenueMovies)
        mostRecentMovies = initPagedLiveData(::getMostRecentMovies)
    }

    fun displayMovieDetails(movie: Movie) {
        router.navigateTo(MovieDetailsScreen(movie))
    }

    private fun createDefaultPageListConfig() = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()

    private fun initPagedLiveData(fetch: (Int, onReady: (List<Movie>) -> Unit) -> Unit): LiveData<PagedList<Movie>> =
        LivePagedListBuilder(
            DiscoveryDataSourceFactory(fetch),
            createDefaultPageListConfig()
        ).build()


    private fun fetchDiscoveryItems(
        onReady: (List<Movie>) -> Unit,
        onFetch: suspend () -> Result<DiscoveryItems>
    ) {
        launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { onFetch() }) {
                is Result.Success -> onReady(result.data.results)
                is Result.Error ->
                    loge(result.exception) { "Popular movies loading error: ${result.exception.message}" }
            }
        }
    }

    private fun getPopularMovies(page: Int, onReady: (List<Movie>) -> Unit) =
        fetchDiscoveryItems(onReady, { discoveryInteractor.getPopularMovies(page) })

    private fun getTopRatedMovies(page: Int, onReady: (List<Movie>) -> Unit) =
        fetchDiscoveryItems(onReady, { discoveryInteractor.getTopRatedMovies(page) })

    private fun getTopRevenueMovies(page: Int, onReady: (List<Movie>) -> Unit) =
        fetchDiscoveryItems(onReady, { discoveryInteractor.getTopRevenueMovies(page) })

    private fun getMostRecentMovies(page: Int, onReady: (List<Movie>) -> Unit) =
        fetchDiscoveryItems(onReady, { discoveryInteractor.getMostRecentMovies(page) })
}
