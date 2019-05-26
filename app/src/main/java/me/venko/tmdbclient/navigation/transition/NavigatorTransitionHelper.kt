package me.venko.tmdbclient.navigation.transition

import android.content.Context
import android.transition.Fade
import android.transition.TransitionInflater
import android.transition.TransitionSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import me.venko.tmdbclient.R
import javax.inject.Inject

/**
 * @author Victor Kosenko
 *
 */
class NavigatorTransitionHelper @Inject constructor(val context: Context) {
    fun setupGenericTransition(fragmentTransaction: FragmentTransaction, transitionInfo: TransitionInfo?) {
        transitionInfo?.let {
            fragmentTransaction.setCustomAnimations(it.enter, it.exit, it.popEnter, it.popExit)
        }
    }

    fun setupSharedTransition(
        transitionInfo: TransitionInfo,
        initiator: Fragment?,
        nextFragment: Fragment?,
        ft: FragmentTransaction?
    ) {
        if (initiator != null) {
            val enterFade = Fade()
            enterFade.startDelay = MOVE_DEFAULT_TIME + FADE_DEFAULT_TIME - DELAY_SHIFT
            enterFade.duration =
                FADE_DEFAULT_TIME

            val exitFade = Fade()
            exitFade.duration =
                FADE_DEFAULT_TIME

            // 1. Exit for Previous Fragment
            initiator.exitTransition = exitFade
            initiator.reenterTransition = enterFade

            // 2. Shared Elements Transition
            val enterTransitionSet = TransitionSet().apply {
                addTransition(
                    TransitionInflater
                        .from(context)
                        .inflateTransition(R.transition.shared_transition_default)
                )
                addTransition(TextSizeTransition())
                duration =
                    MOVE_DEFAULT_TIME
                startDelay =
                    FADE_DEFAULT_TIME
            }
            nextFragment?.sharedElementEnterTransition = enterTransitionSet

            // 3. Enter Transition for New Fragment
            nextFragment?.enterTransition = enterFade.clone()
            nextFragment?.returnTransition = exitFade.clone()

            transitionInfo.sharedElements?.forEach {
                ft?.addSharedElement(it, it.transitionName)
            }
        }
    }

    companion object {
        private const val MOVE_DEFAULT_TIME = 360L
        private const val FADE_DEFAULT_TIME = 90L
        private const val DELAY_SHIFT = 75L
    }
}
