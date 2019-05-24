package me.venko.tmdbclient.presentation.root

import me.venko.tmdbclient.di.Injector
import me.venko.tmdbclient.navigation.DiscoveryScreen
import me.venko.tmdbclient.presentation.common.viewmodel.BaseViewModel
import ru.terrakok.cicerone.Router
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
    lateinit var router: Router

    fun coldStart() {
        router.newRootScreen(DiscoveryScreen())
    }
}
