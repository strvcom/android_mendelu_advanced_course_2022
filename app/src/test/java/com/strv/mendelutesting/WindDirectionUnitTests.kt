package com.strv.mendelutesting

import com.strv.mendelutesting.logic.WindDirectionsEnum
import org.junit.Assert.assertEquals
import org.junit.Test

class WindDirectionUnitTests {

    @Test
    fun windDirection_isCorrect1() {
        assertEquals(
            WindDirectionsEnum.NORTH,
            WindDirectionsEnum.fromDegrees(0)
        )
    }

    @Test
    fun windDirection_isCorrect2() {
        assertEquals(
            WindDirectionsEnum.NORTH,
            WindDirectionsEnum.fromDegrees(360)
        )
    }

    @Test
    fun windDirection_isCorrect3() {
        assertEquals(
            WindDirectionsEnum.NORTH,
            WindDirectionsEnum.fromDegrees(721)
        )
    }

    @Test
    fun windDirection_isCorrect4() {
        assertEquals(
            WindDirectionsEnum.EAST,
            WindDirectionsEnum.fromDegrees(90)
        )
    }

    @Test
    fun windDirection_isCorrect5() {
        assertEquals(
            WindDirectionsEnum.SOUTH,
            WindDirectionsEnum.fromDegrees(180)
        )
    }

    @Test
    fun windDirection_isCorrect6() {
        assertEquals(
            WindDirectionsEnum.WEST,
            WindDirectionsEnum.fromDegrees(270)
        )
    }

    @Test
    fun windDirection_isCorrect7() {
        assertEquals(
            WindDirectionsEnum.NORTH_EAST,
            WindDirectionsEnum.fromDegrees(47)
        )
    }

    @Test
    fun windDirection_isCorrect8() {
        assertEquals(
            WindDirectionsEnum.SOUTH_EAST,
            WindDirectionsEnum.fromDegrees(135)
        )
    }

    @Test
    fun windDirection_isCorrect9() {
        assertEquals(
            WindDirectionsEnum.SOUTH_WEST,
            WindDirectionsEnum.fromDegrees(225)
        )
    }

    @Test
    fun windDirection_isCorrect10() {
        assertEquals(
            WindDirectionsEnum.NORTH_WEST,
            WindDirectionsEnum.fromDegrees(315)
        )
    }

    @Test
    fun windDirection_isCorrect11() {
        assertEquals(
            WindDirectionsEnum.NORTH_WEST,
            WindDirectionsEnum.fromDegrees(337)
        )
    }

    @Test
    fun windDirection_isCorrect12() {
        assertEquals(
            WindDirectionsEnum.NORTH,
            WindDirectionsEnum.fromDegrees(338)
        )
    }

}
