package me.venko.tmdbclient.navigation.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import me.venko.tmdbclient.R
import me.venko.tmdbclient.navigation.TransitionCommand
import me.venko.tmdbclient.navigation.transition.NavigatorTransitionHelper
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

/**
 * @author Victor Kosenko
 *
 */
@AutoFactory(implementing = [NavigatorFactory::class])
class GenericNavigator(
    activity: FragmentActivity,
    @Provided val transitionHelper: NavigatorTransitionHelper
) : SupportAppNavigator(activity, R.id.viewContainer) {

    override fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment,
        ft: FragmentTransaction
    ) {
        super.setupFragmentTransaction(command, currentFragment, nextFragment, ft)
        val transitionInfo = if (command is TransitionCommand) {
            command.transitionInfo
        } else {
            null
        }
        if (transitionInfo?.sharedElements != null) {
            transitionHelper.setupSharedTransition(transitionInfo, currentFragment, nextFragment, ft)
        } else {
            transitionHelper.setupGenericTransition(ft, transitionInfo)
        }
    }
}
