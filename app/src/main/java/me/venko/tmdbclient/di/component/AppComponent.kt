package me.venko.tmdbclient.di.component

import dagger.Component
import me.venko.tmdbclient.di.module.ApiModule
import me.venko.tmdbclient.di.module.AppModule
import me.venko.tmdbclient.di.module.CoreModule
import me.venko.tmdbclient.presentation.root.AppRootViewModel
import me.venko.tmdbclient.presentation.root.RootActivity
import me.venko.tmdbclient.TmdbClientApp
import me.venko.tmdbclient.di.module.NavigationModule
import javax.inject.Singleton

/**
 * @author Victor Kosenko
 *
 */
@Singleton
@Component(modules = [AppModule::class, ApiModule::class, NavigationModule::class, CoreModule::class])
interface AppComponent {

    fun inject(app: TmdbClientApp)

    fun inject(viewModel: AppRootViewModel)
    fun inject(activity: RootActivity)
}
