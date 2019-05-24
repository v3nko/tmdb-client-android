package me.venko.tmdbclient.data.api

import me.venko.tmdbclient.data.common.PropertiesManager
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


/**
 * @author Victor Kosenko
 *
 */
class ClientIdentityInterceptor @Inject constructor(private val propertiesManager: PropertiesManager) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val apiKey = propertiesManager.tmdbApiKey
        var request = chain.request()
        val url = request.url().newBuilder().addQueryParameter("api_key", apiKey).build()
        request = request.newBuilder().url(url).build()
        return chain.proceed(request)
    }
}
