package me.venko.tmdbclient.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import me.venko.tmdbclient.data.common.PropertiesManager
import javax.inject.Singleton

/**
 * @author Victor Kosenko
 *
 */
@Module(includes = [ContextModule::class])
class AppModule {

    @Singleton
    @Provides
    fun providePropertiesManager(context: Context) = PropertiesManager(context)
}
