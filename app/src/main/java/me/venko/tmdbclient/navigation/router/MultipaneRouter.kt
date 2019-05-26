package me.venko.tmdbclient.navigation.router

import me.venko.tmdbclient.navigation.ForwardToDetails
import me.venko.tmdbclient.navigation.ForwardToDetailsRoot
import me.venko.tmdbclient.navigation.transition.TransitionInfo
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Replace

/**
 * @author Victor Kosenko
 *
 */
class MultipaneRouter : Router() {

    /**
     * Open new screen and add it to the screens chain. In tablet mode details screen will be
     * replaced without adding to chain.
     *
     * @param screen Screen
     * @param transitionInfo Transition behavior description
     */
    fun navigateToDetailsRoot(screen: Screen, transitionInfo: TransitionInfo? = null) {
        executeCommands(ForwardToDetailsRoot(screen, transitionInfo))
    }

    /**
     * Open new screen and add it to the screens chain. In tablet mode this screen will be added to
     * details screen backstack.
     *
     * @param screen Screen
     * @param transitionInfo Transition behavior description
     */
    fun navigateToDetails(screen: Screen, transitionInfo: TransitionInfo) {
        executeCommands(ForwardToDetails(screen, transitionInfo))
    }

    /**
     * Clear current stack and open several screens inside single transaction. First screen in chain
     * will be added to master container and others to details container. This will result in next chain:
     * master: screens(0) | details: screens(1) -> ... -> screens(n)
     * Chain should contain at least two screens
     * @param screens
     * @param transitionInfo Transition behavior description
     */
    @Suppress("SpreadOperator")
    fun newRootChainDetails(vararg screens: Screen, transitionInfo: TransitionInfo? = null) {
        check(screens.size > 1) { "Chain should contain at least two screens" }
        val commands = arrayOfNulls<Command>(screens.size + 1)
        commands[0] = BackTo(null)
        if (screens.isNotEmpty()) {
            commands[1] = Replace(screens[0])
            commands[2] = ForwardToDetailsRoot(screens[1], transitionInfo)
            for (i in 2 until screens.size) {
                commands[i + 1] = ForwardToDetails(screens[i], transitionInfo)
            }
        }
        executeCommands(*commands)
    }
}
