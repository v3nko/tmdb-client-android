package me.venko.tmdbclient.presentation.common.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import me.venko.tmdbclient.R
import me.venko.tmdbclient.di.Injector
import me.venko.tmdbclient.navigation.navigator.NavigatorFactory
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

/**
 * @author Victor Kosenko
 *
 */
@SuppressLint("Registered")
abstract class BaseActivity : FragmentActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private lateinit var navigator: Navigator

    protected val isTablet: Boolean by lazy {
        resources.getBoolean(R.bool.is_tablet_mode)
    }

    protected open val layoutId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val navigatorType = if (!isTablet) {
            NavigatorFactory.NAVIGATOR_GENERIC
        } else {
            NavigatorFactory.NAVIGATOR_TABLET
        }
        navigator = Injector.appComponent()
            .appNavigatorsFactories()
            .getValue(navigatorType)
            .createNavigator(this)
        layoutId.takeIf { it != 0 }?.let(::setContentView)
    }

    protected fun isModeChangedToTablet(savedInstanceState: Bundle?): Boolean =
        savedInstanceState?.getBoolean(KEY_IS_TABLET) == false && isTablet

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putBoolean(KEY_IS_TABLET, isTablet)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }

    companion object {
        private const val KEY_IS_TABLET = "isTablet"
    }
}
