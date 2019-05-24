package me.venko.tmdbclient.presentation.common.view

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import me.venko.tmdbclient.R
import java.lang.Math.round

class HeightAspectRatioFrameLayout @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0
) : FrameLayout(context, attrs, defStyle) {

    private var aspectRatio = 0f

    init {
        attrs?.let {
            val typedArray = context.obtainStyledAttributes(
                it,
                R.styleable.HeightAspectRatioFrameLayout,
                defStyle,
                0
            )
            aspectRatio = typedArray.getFloat(R.styleable.HeightAspectRatioFrameLayout_aspectRatio, 0f)
            typedArray.recycle()
        }
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        if (aspectRatio > 0) {
            val height = MeasureSpec.getSize(heightMeasureSpec)
            val width = round(MeasureSpec.getSize(heightMeasureSpec) / aspectRatio)
            val finalWidthMeasureSpec = MeasureSpec.makeMeasureSpec(width, MeasureSpec.EXACTLY)
            val finalHeightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY)
            super.onMeasure(finalWidthMeasureSpec, finalHeightMeasureSpec)
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        }

    }
}
