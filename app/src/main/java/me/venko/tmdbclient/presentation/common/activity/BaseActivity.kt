package me.venko.tmdbclient.presentation.common.activity

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import me.venko.tmdbclient.navigation.NavigatorFactory
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
    lateinit var navigatorFactory: NavigatorFactory
    @Inject
    lateinit var navigatorHolder: NavigatorHolder
    private lateinit var navigator: Navigator

    protected open val layoutId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigator = navigatorFactory.createNavigator(this)
        layoutId.takeIf { it != 0 }?.let(::setContentView)
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}
