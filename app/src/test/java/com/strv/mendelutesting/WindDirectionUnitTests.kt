package com.strv.mendelutesting

import com.strv.mendelutesting.logic.WindDirectionsEnum
import org.junit.Assert.assertEquals
import org.junit.Test

class WindDirectionUnitTests {

    // Priority to do: High
    @Test
    fun windDirection_isCorrect1() {
        assertEquals(
            WindDirectionsEnum.NORTH,
            WindDirectionsEnum.fromDegrees(0)
        )
    }

    // Priority to do: High
    @Test
    fun windDirection_isCorrect2() {
        assertEquals(
            WindDirectionsEnum.NORTH,
            WindDirectionsEnum.fromDegrees(360)
        )
    }

    // Priority to do: High
    @Test
    fun windDirection_isCorrect3() {
        assertEquals(
            WindDirectionsEnum.NORTH,
            WindDirectionsEnum.fromDegrees(721)
        )
    }

    // Priority to do: Low
    @Test
    fun windDirection_isCorrect4() {
        assertEquals(
            WindDirectionsEnum.EAST,
            WindDirectionsEnum.fromDegrees(90)
        )
    }

    // Priority to do: Low
    @Test
    fun windDirection_isCorrect5() {
        assertEquals(
            WindDirectionsEnum.SOUTH,
            WindDirectionsEnum.fromDegrees(180)
        )
    }

    // Priority to do: Low
    @Test
    fun windDirection_isCorrect6() {
        assertEquals(
            WindDirectionsEnum.WEST,
            WindDirectionsEnum.fromDegrees(270)
        )
    }

    // Priority to do: Low
    @Test
    fun windDirection_isCorrect7() {
        assertEquals(
            WindDirectionsEnum.NORTH_EAST,
            WindDirectionsEnum.fromDegrees(47)
        )
    }

    // Priority to do: Low
    @Test
    fun windDirection_isCorrect8() {
        assertEquals(
            WindDirectionsEnum.SOUTH_EAST,
            WindDirectionsEnum.fromDegrees(135)
        )
    }

    // Priority to do: Low
    @Test
    fun windDirection_isCorrect9() {
        assertEquals(
            WindDirectionsEnum.SOUTH_WEST,
            WindDirectionsEnum.fromDegrees(225)
        )
    }

    // Priority to do: Low
    @Test
    fun windDirection_isCorrect10() {
        assertEquals(
            WindDirectionsEnum.NORTH_WEST,
            WindDirectionsEnum.fromDegrees(315)
        )
    }

    // Priority to do: Low
    @Test
    fun windDirection_isCorrect11() {
        assertEquals(
            WindDirectionsEnum.NORTH_WEST,
            WindDirectionsEnum.fromDegrees(337)
        )
    }

    // Priority to do: High
    @Test
    fun windDirection_isCorrect12() {
        assertEquals(
            WindDirectionsEnum.NORTH,
            WindDirectionsEnum.fromDegrees(338)
        )
    }

}
