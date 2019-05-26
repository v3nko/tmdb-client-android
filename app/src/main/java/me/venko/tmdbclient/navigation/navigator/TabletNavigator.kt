package me.venko.tmdbclient.navigation.navigator

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.google.auto.factory.AutoFactory
import com.google.auto.factory.Provided
import me.venko.tmdbclient.R
import me.venko.tmdbclient.navigation.TransitionCommand
import me.venko.tmdbclient.navigation.transition.NavigatorTransitionHelper
import ru.terrakok.cicerone.commands.Command

/**
 * @author Victor Kosenko
 *
 */
@AutoFactory(implementing = [NavigatorFactory::class])
class TabletNavigator(
    activity: FragmentActivity,
    @Provided var transitionHelper: NavigatorTransitionHelper
) : TabletSupportAppNavigator(activity, R.id.viewContainer, R.id.viewContainerDetails) {

    override fun setupFragmentTransaction(
        command: Command,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction
    ) {
        super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction)
        if (command is TransitionCommand) {
            transitionHelper.setupGenericTransition(fragmentTransaction, command.transitionInfo)
        }
    }

}
