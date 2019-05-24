package me.venko.tmdbclient.data.common

import android.content.Context
import java.util.*
import javax.inject.Inject

/**
 * @author Victor Kosenko
 *
 */
class PropertiesManager @Inject constructor(private val context: Context) {

    private val instance: Properties by lazy {
        val inputStream = context.assets.open(CONFIG_FILE)
        val properties = Properties()
        properties.load(inputStream)
        properties
    }

    val tmdbApiKey: String
        get() = getStringValue(KEY_TMDB_API_KEY)

    private fun getBoolValue(key: String, default: Boolean): Boolean {
        return instance.getProperty(key)?.toBoolean() ?: default
    }

    private fun getStringValue(key: String, default: String = "") =
        instance.getProperty(key, default)

    companion object {
        private const val CONFIG_FILE = "config/config.properties"
        private const val KEY_TMDB_API_KEY = "tmdb.apiKey"
    }
}
