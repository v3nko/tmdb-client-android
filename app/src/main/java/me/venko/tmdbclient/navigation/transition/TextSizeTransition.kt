package me.venko.tmdbclient.navigation.transition

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.transition.Transition
import android.transition.TransitionValues
import android.util.AttributeSet
import android.util.Property
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.TextView

/**
 * @author Victor Kosenko
 *
 */
class TextSizeTransition : Transition {

    constructor()

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs)

    override fun getTransitionProperties(): Array<String> {
        return TRANSITION_PROPERTIES
    }

    override fun captureStartValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    override fun captureEndValues(transitionValues: TransitionValues) {
        captureValues(transitionValues)
    }

    private fun captureValues(transitionValues: TransitionValues) {
        val view = transitionValues.view
        if (view is TextView) {
            transitionValues.values[PROP_TEXT_SIZE] = view.textSize
        }
    }

    override fun createAnimator(
        sceneRoot: ViewGroup,
        startValues: TransitionValues?,
        endValues: TransitionValues?
    ): Animator? {
        if (startValues == null || endValues == null) {
            return null
        }
        val startSize = startValues.values[PROP_TEXT_SIZE] as Float
        val endSize = endValues.values[PROP_TEXT_SIZE] as Float

        return if (startSize == endSize) {
            null
        } else {
            val view = endValues.view as TextView
            view.setTextSize(TypedValue.COMPLEX_UNIT_PX, startSize)
            ObjectAnimator.ofFloat(view, TEXT_SIZE, startSize, endSize)
        }
    }

    companion object {
        private const val PROP_TEXT_SIZE = "prop_text_size"

        private val TRANSITION_PROPERTIES = arrayOf(PROP_TEXT_SIZE)

        private val TEXT_SIZE =
            object : Property<TextView, Float>(Float::class.java, "textSize") {
                override fun get(textView: TextView): Float {
                    return textView.textSize
                }

                override fun set(textView: TextView, textSizePixels: Float?) {
                    textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, textSizePixels!!)
                }
            }
    }
}
