package me.venko.tmdbclient.navigation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.google.auto.factory.AutoFactory
import me.venko.tmdbclient.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import ru.terrakok.cicerone.commands.Command

/**
 * @author Victor Kosenko
 *
 */
@AutoFactory(implementing = [NavigatorFactory::class])
class GenericNavigator(
    activity: FragmentActivity
) : SupportAppNavigator(activity, R.id.viewContainer) {
    override fun setupFragmentTransaction(
        command: Command?,
        currentFragment: Fragment?,
        nextFragment: Fragment?,
        fragmentTransaction: FragmentTransaction?
    ) {
        super.setupFragmentTransaction(command, currentFragment, nextFragment, fragmentTransaction)
        fragmentTransaction?.setCustomAnimations(
            R.anim.fade_in_zoom_in,
            R.anim.fade_out_slide_out_bottom,
            R.anim.fade_in_zoom_in,
            R.anim.fade_out_slide_out_bottom
        )
    }
}
