package me.venko.tmdbclient.navigation.transition

import android.view.View
import androidx.annotation.AnimRes
import androidx.annotation.AnimatorRes
import me.venko.tmdbclient.R

/**
 * @author Victor Kosenko
 *
 */
data class TransitionInfo(
    val sharedElements: List<View>? = null,
    @AnimatorRes @AnimRes val enter: Int = R.anim.fade_in_zoom_in,
    @AnimatorRes @AnimRes val exit: Int = R.anim.fade_out_slide_out_bottom,
    @AnimatorRes @AnimRes val popEnter: Int = R.anim.fade_in_zoom_in,
    @AnimatorRes @AnimRes val popExit: Int = R.anim.fade_out_slide_out_bottom
) {
    companion object {
        fun default(): TransitionInfo = TransitionInfo()

        fun shared(sharedElements: List<View>) = TransitionInfo(sharedElements)
    }
}
