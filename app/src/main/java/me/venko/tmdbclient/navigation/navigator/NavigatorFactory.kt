package me.venko.tmdbclient.navigation.navigator

import androidx.fragment.app.FragmentActivity
import ru.terrakok.cicerone.Navigator

/**
 * @author Victor Kosenko
 *
 */
interface NavigatorFactory {

    fun createNavigator(activity: FragmentActivity): Navigator

    companion object {
        const val NAVIGATOR_GENERIC = 0
        const val NAVIGATOR_TABLET = 1
    }
}
