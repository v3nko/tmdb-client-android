package me.venko.tmdbclient.navigation

import me.venko.tmdbclient.navigation.transition.TransitionInfo
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.commands.Forward

/**
 *
 * Opens new screen in phone mode and replaces details panel screen in tablet mode
 *
 * @author Victor Kosenko
 *
 */
interface TransitionCommand {
    val transitionInfo: TransitionInfo?
}

class ForwardToDetailsRoot(
    screen: Screen,
    override val transitionInfo: TransitionInfo?
) : Forward(screen), TransitionCommand

class ForwardToDetails(
    screen: Screen,
    override val transitionInfo: TransitionInfo?
) : Forward(screen), TransitionCommand
