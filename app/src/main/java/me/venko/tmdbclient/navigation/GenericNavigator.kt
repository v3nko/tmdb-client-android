package me.venko.tmdbclient.navigation

import androidx.fragment.app.FragmentActivity
import com.google.auto.factory.AutoFactory
import me.venko.tmdbclient.R
import ru.terrakok.cicerone.android.support.SupportAppNavigator

/**
 * @author Victor Kosenko
 *
 */
@AutoFactory(implementing = [NavigatorFactory::class])
class GenericNavigator(
    activity: FragmentActivity
) : SupportAppNavigator(activity, R.id.viewContainer)
