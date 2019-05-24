package me.venko.tmdbclient.navigation

import androidx.fragment.app.FragmentActivity
import ru.terrakok.cicerone.Navigator

/**
 * @author Victor Kosenko
 *
 */
interface NavigatorFactory {

    fun createNavigator(activity: FragmentActivity): Navigator
}
