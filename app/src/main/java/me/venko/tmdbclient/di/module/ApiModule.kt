package me.venko.tmdbclient.di.module

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import me.venko.tmdbclient.data.api.ClientIdentityInterceptor
import me.venko.tmdbclient.data.api.TmdbApi
import me.venko.tmdbclient.data.common.PropertiesManager
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit


/**
 * @author Victor Kosenko
 *
 */
@Module
class ApiModule {

    @Provides
    fun provideApi(builder: Retrofit.Builder): TmdbApi =
        builder.baseUrl(API_HOST_BASE)
            .build()
            .create(TmdbApi::class.java)

    @Provides
    fun provideRetrofitBuilder(client: OkHttpClient, moshi: Moshi): Retrofit.Builder =
        Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .addCallAdapterFactory(CoroutineCallAdapterFactory())

    @Provides
    fun provideOkHttpClient(
        logger: HttpLoggingInterceptor,
        identityInterceptor: ClientIdentityInterceptor
    ): OkHttpClient =
        OkHttpClient.Builder()
            .readTimeout(TIMEOUT, TimeUnit.SECONDS)
            .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT, TimeUnit.SECONDS)
            .addInterceptor(logger)
            .addInterceptor(identityInterceptor)
            .build()

    @Provides
    fun provideLoggingInterceptor() =
        HttpLoggingInterceptor()
            .apply { level = HttpLoggingInterceptor.Level.BODY }

    @Provides
    fun provideClientIdentityInterceptor(propertiesManager: PropertiesManager) =
        ClientIdentityInterceptor(propertiesManager)

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder().build()

    companion object {

        const val TIMEOUT = 30L

        const val API_HOST_BASE = "https://api.themoviedb.org/3/"
    }
}
