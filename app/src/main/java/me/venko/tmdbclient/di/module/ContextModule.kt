package me.venko.tmdbclient.di.module

import android.content.Context
import dagger.Module
import dagger.Provides

/**
 * @author Victor Kosenko
 *
 */
@Module
class ContextModule(private val context: Context) {

    @Provides
    fun provideContext(): Context = context
}
