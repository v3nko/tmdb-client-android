package me.venko.tmdbclient.presentation.discovery

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
import me.venko.tmdbclient.presentation.common.view.ViewContentState
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
    private val _popularContentState = MutableLiveData<ViewContentState>().apply {
        value = ViewContentState.LOADING
    }
    val popularContentState = _popularContentState as LiveData<ViewContentState>


    lateinit var topRatedMovies: LiveData<PagedList<Movie>>
    private val _topRatedContentState = MutableLiveData<ViewContentState>().apply {
        value = ViewContentState.LOADING
    }
    val topRatedContentState = _topRatedContentState as LiveData<ViewContentState>

    lateinit var topRevenueMovies: LiveData<PagedList<Movie>>
    private val _topRevenueContentState = MutableLiveData<ViewContentState>().apply {
        value = ViewContentState.LOADING
    }
    val topRevenueContentState = _topRevenueContentState as LiveData<ViewContentState>

    lateinit var mostRecentMovies: LiveData<PagedList<Movie>>
    private val _mostRecentContentState = MutableLiveData<ViewContentState>().apply {
        value = ViewContentState.LOADING
    }
    val mostRecentContentState = _mostRecentContentState as LiveData<ViewContentState>


    init {
        Injector.appComponent().inject(this)

        popularMovies = initPagedLiveData(::getPopularMovies)
        topRatedMovies = initPagedLiveData(::getTopRatedMovies)
        topRevenueMovies = initPagedLiveData(::getTopRevenueMovies)
        mostRecentMovies = initPagedLiveData(::getMostRecentMovies)
    }

    fun displayMovieDetails(movie: Movie) {
        router.navigateTo(MovieDetailsScreen(movie))
    }

    private fun createDefaultPagedListConfig() = PagedList.Config.Builder()
        .setEnablePlaceholders(false)
        .setPageSize(20)
        .build()

    private fun initPagedLiveData(fetch: (Int, onReady: (List<Movie>) -> Unit) -> Unit): LiveData<PagedList<Movie>> =
        LivePagedListBuilder(
            DiscoveryDataSourceFactory(fetch),
            createDefaultPagedListConfig()
        ).build()


    private fun fetchDiscoveryItems(
        onReady: (List<Movie>) -> Unit,
        stateData: MutableLiveData<ViewContentState>,
        onFetch: suspend () -> Result<DiscoveryItems>
    ) {
        launch(Dispatchers.Main) {
            when (val result = withContext(Dispatchers.IO) { onFetch() }) {
                is Result.Success -> {
                    onReady(result.data.results)
                    stateData.value =
                        if (result.data.results.isEmpty() && stateData.value == ViewContentState.LOADING) {
                            ViewContentState.EMPTY
                        } else {
                            ViewContentState.READY
                        }
                }
                is Result.Error -> {
                    loge(result.exception) { "Popular movies loading error: ${result.exception.message}" }
                    stateData.value = ViewContentState.ERROR
                }
            }
        }
    }

    private fun getPopularMovies(page: Int, onReady: (List<Movie>) -> Unit) =
        fetchDiscoveryItems(onReady, _popularContentState) { discoveryInteractor.getPopularMovies(page) }

    private fun getTopRatedMovies(page: Int, onReady: (List<Movie>) -> Unit) =
        fetchDiscoveryItems(onReady, _topRatedContentState) { discoveryInteractor.getTopRatedMovies(page) }

    private fun getTopRevenueMovies(page: Int, onReady: (List<Movie>) -> Unit) =
        fetchDiscoveryItems(onReady, _topRevenueContentState) { discoveryInteractor.getTopRevenueMovies(page) }

    private fun getMostRecentMovies(page: Int, onReady: (List<Movie>) -> Unit) =
        fetchDiscoveryItems(onReady, _mostRecentContentState) { discoveryInteractor.getMostRecentMovies(page) }
}
