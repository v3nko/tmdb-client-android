package me.venko.tmdbclient.navigation

import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import javax.inject.Inject

/**
 * @author Victor Kosenko
 */
class CiceroneHolder @Inject constructor(router: Router) {
    val cicerone: Cicerone<Router> = Cicerone.create(router)
}
