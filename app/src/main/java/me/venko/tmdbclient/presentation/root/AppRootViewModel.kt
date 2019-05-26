package me.venko.tmdbclient.presentation.root

import me.venko.tmdbclient.di.Injector
import me.venko.tmdbclient.navigation.DiscoveryScreen
import me.venko.tmdbclient.navigation.MovieDetailsPlaceholderScreen
import me.venko.tmdbclient.navigation.router.MultipaneRouter
import me.venko.tmdbclient.presentation.common.viewmodel.BaseViewModel
import javax.inject.Inject

/**
 * @author Victor Kosenko
 *
 */
class AppRootViewModel : BaseViewModel() {

    init {
        Injector.appComponent().inject(this)
    }

    @Inject
    lateinit var router: MultipaneRouter

    fun coldStart(isTablet: Boolean) {
        if (isTablet) {
            router.newRootChainDetails(DiscoveryScreen(), MovieDetailsPlaceholderScreen())
        } else {
            router.newRootScreen(DiscoveryScreen())
        }
    }
}
