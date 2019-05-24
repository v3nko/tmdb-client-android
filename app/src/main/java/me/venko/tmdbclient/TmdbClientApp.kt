package me.venko.tmdbclient

import android.app.Application
import me.venko.tmdbclient.data.common.PropertiesManager
import me.venko.tmdbclient.di.Injector
import javax.inject.Inject

/**
 * @author Victor Kosenko
 *
 */
class TmdbClientApp : Application() {

    @Inject
    internal lateinit var propertiesManager: PropertiesManager


    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
    }
}
