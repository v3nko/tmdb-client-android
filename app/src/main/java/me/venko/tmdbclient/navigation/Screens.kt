package me.venko.tmdbclient.navigation

import androidx.fragment.app.Fragment
import me.venko.tmdbclient.presentation.discovery.DiscoveryFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

/**
 * @author Victor Kosenko
 *
 */
class DiscoveryScreen : SupportAppScreen() {
    override fun getFragment(): Fragment = DiscoveryFragment()
}
