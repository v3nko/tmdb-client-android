package me.venko.tmdbclient.utils

import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

/**
 * @author Victor Kosenko
 *
 */
fun <T : Fragment> T.withArguments(vararg params: Pair<String, Any?>): T {
    arguments = bundleOf(*params)
    return this
}
