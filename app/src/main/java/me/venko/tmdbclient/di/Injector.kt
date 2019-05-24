package me.venko.tmdbclient.di

import me.venko.tmdbclient.di.module.ApiModule
import me.venko.tmdbclient.di.module.AppModule
import me.venko.tmdbclient.di.module.ContextModule
import me.venko.tmdbclient.TmdbClientApp
import me.venko.tmdbclient.di.component.AppComponent
import me.venko.tmdbclient.di.component.DaggerAppComponent

/**
 * @author Victor Kosenko
 *
 */
object Injector {

    private lateinit var appComponent: AppComponent

    fun appComponent() = appComponent

    fun init(app: TmdbClientApp) {
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule())
            .contextModule(ContextModule(app))
            .apiModule(ApiModule())
            .build()
            .also { it.inject(app) }
    }
}
