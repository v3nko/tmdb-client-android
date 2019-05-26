package me.venko.tmdbclient.di.module

import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import me.venko.tmdbclient.navigation.CiceroneHolder
import me.venko.tmdbclient.navigation.navigator.GenericNavigatorFactory
import me.venko.tmdbclient.navigation.navigator.NavigatorFactory
import me.venko.tmdbclient.navigation.navigator.TabletNavigatorFactory
import me.venko.tmdbclient.navigation.router.MultipaneRouter
import me.venko.tmdbclient.navigation.transition.NavigatorTransitionHelper
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Provider
import javax.inject.Singleton

/**
 * @author Victor Kosenko
 *
 */
@Module(includes = [ContextModule::class])
class NavigationModule {

    @Singleton
    @Provides
    fun provideCiceroneHolder(router: MultipaneRouter) = CiceroneHolder(router)

    @Singleton
    @Provides
    fun provideLocalRouter(): MultipaneRouter = MultipaneRouter()

    @Provides
    fun provideNavigationHolder(ciceroneHolder: CiceroneHolder): NavigatorHolder =
        ciceroneHolder.cicerone.navigatorHolder

    @Singleton
    @Provides
    fun provideTransitionHelper(context: Context): NavigatorTransitionHelper = NavigatorTransitionHelper(context)

    @Provides
    @IntoMap
    @IntKey(NavigatorFactory.NAVIGATOR_GENERIC)
    fun provideGenericNavigator(provider: Provider<NavigatorTransitionHelper>): NavigatorFactory {
        return GenericNavigatorFactory(provider)
    }

    @Provides
    @IntoMap
    @IntKey(NavigatorFactory.NAVIGATOR_TABLET)
    fun provideTabletNavigator(provider: Provider<NavigatorTransitionHelper>): NavigatorFactory {
        return TabletNavigatorFactory(provider)
    }
}
