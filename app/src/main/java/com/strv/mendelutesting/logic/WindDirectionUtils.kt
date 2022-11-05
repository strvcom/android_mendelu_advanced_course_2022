package com.strv.mendelutesting.logic

import androidx.annotation.StringRes
import com.strv.mendelutesting.R
import kotlin.math.roundToInt

enum class WindDirectionsEnum(
    @StringRes val label: Int
) {
    NORTH(label = R.string.wind_direction_n),
    NORTH_EAST(label = R.string.wind_direction_ne),
    EAST(label = R.string.wind_direction_e),
    SOUTH_EAST(label = R.string.wind_direction_se),
    SOUTH(label = R.string.wind_direction_s),
    SOUTH_WEST(label = R.string.wind_direction_sw),
    WEST(label = R.string.wind_direction_w),
    NORTH_WEST(label = R.string.wind_direction_nw);

    companion object {
        /**
         * @link 'https://slideplayer.com/slide/17852123/106/images/17/Wind+Direction+%28degrees%29+Meteorologists+generally+use+degrees.jpg'
         */
        fun fromDegrees(degrees: Int): WindDirectionsEnum {
            val directionIndex = (degrees * 8 / 360f).roundToInt() % 8
            return values()[directionIndex]
        }
    }
}