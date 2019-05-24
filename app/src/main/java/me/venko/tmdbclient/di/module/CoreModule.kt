package me.venko.tmdbclient.di.module

import dagger.Module
import dagger.Provides
import me.venko.tmdbclient.core.interactor.MoviesDiscoveryInteractor
import me.venko.tmdbclient.core.repo.MoviesRepository
import me.venko.tmdbclient.data.api.TmdbApi
import me.venko.tmdbclient.data.repo.TmdbRepository

/**
 * @author Victor Kosenko
 *
 */
@Module
class CoreModule {

    @Provides
    fun provideMoviesRepository(api: TmdbApi): MoviesRepository = TmdbRepository(api)

    @Provides
    fun provideDiscoveryInteractor(repository: MoviesRepository) =
        MoviesDiscoveryInteractor(repository)
}
