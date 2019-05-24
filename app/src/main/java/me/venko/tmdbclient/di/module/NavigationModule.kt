package me.venko.tmdbclient.di.module

import dagger.Module
import dagger.Provides
import me.venko.tmdbclient.navigation.CiceroneHolder
import me.venko.tmdbclient.navigation.GenericNavigatorFactory
import me.venko.tmdbclient.navigation.NavigatorFactory
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.Router
import javax.inject.Singleton

/**
 * @author Victor Kosenko
 *
 */
@Module(includes = [ContextModule::class])
class NavigationModule {

    @Singleton
    @Provides
    fun provideCiceroneHolder(router: Router) = CiceroneHolder(router)

    @Singleton
    @Provides
    fun provideLocalRouter() = Router()

    @Provides
    fun provideNavigationHolder(ciceroneHolder: CiceroneHolder): NavigatorHolder =
        ciceroneHolder.cicerone.navigatorHolder

    @Provides
    fun provideGenericNavigator(): NavigatorFactory = GenericNavigatorFactory()
}
