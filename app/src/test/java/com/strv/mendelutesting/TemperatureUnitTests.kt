package com.strv.mendelutesting

import com.strv.mendelutesting.logic.TemperatureUnitsEnum
import org.junit.Assert.assertEquals
import org.junit.Test

class TemperatureUnitTests {

    @Test
    fun celsiusToKelvin_isCorrect1() {
        assertEquals(
            /* expected = */ 303.2,
            /* actual = */ TemperatureUnitsEnum.convertCelsiusToKelvin(30.0),
            /* delta = */ 0.1
        )
    }

    @Test
    fun celsiusToKelvin_isCorrect2() {
        assertEquals(
            /* expected = */ 273.15,
            /* actual = */ TemperatureUnitsEnum.convertCelsiusToKelvin(0.0),
            /* delta = */ 0.1
        )
    }

    @Test
    fun celsiusToKelvin_isCorrect3() {
        assertEquals(
            /* expected = */ 269.45,
            /* actual = */ TemperatureUnitsEnum.convertCelsiusToKelvin(-3.7),
            /* delta = */ 0.1
        )
    }

    @Test
    fun celsiusToFahrenheit_isCorrect1() {
        assertEquals(
            /* expected = */ 86.0,
            /* actual = */ TemperatureUnitsEnum.convertCelsiusToFahrenheit(30.0),
            /* delta = */ 0.1
        )
    }

    @Test
    fun celsiusToFahrenheit_isCorrect2() {
        assertEquals(
            /* expected = */ 32.0,
            /* actual = */ TemperatureUnitsEnum.convertCelsiusToFahrenheit(0.0),
            /* delta = */ 0.1
        )
    }

    @Test
    fun celsiusToFahrenheit_isCorrect3() {
        assertEquals(
            /* expected = */ 25.34,
            /* actual = */ TemperatureUnitsEnum.convertCelsiusToFahrenheit(-3.7),
            /* delta = */ 0.1
        )
    }
}
