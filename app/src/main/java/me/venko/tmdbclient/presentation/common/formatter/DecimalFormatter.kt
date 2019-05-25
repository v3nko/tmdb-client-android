package me.venko.tmdbclient.presentation.common.formatter

import java.text.DecimalFormat

/**
 * @author Victor Kosenko
 *
 */
object DecimalFormatter {
    private val decimalFormat2p = DecimalFormat("0.#")

    fun formatRating(rating: Double): String = decimalFormat2p.format(rating)
}
