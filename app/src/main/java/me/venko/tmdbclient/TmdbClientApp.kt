package me.venko.tmdbclient

import android.app.Application
import com.jakewharton.threetenabp.AndroidThreeTen
import me.venko.tmdbclient.di.Injector

/**
 * @author Victor Kosenko
 *
 */
class TmdbClientApp : Application() {

    override fun onCreate() {
        super.onCreate()
        Injector.init(this)
        AndroidThreeTen.init(this)
    }
}
