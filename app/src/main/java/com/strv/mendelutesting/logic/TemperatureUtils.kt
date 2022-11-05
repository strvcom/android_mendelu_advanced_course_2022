package com.strv.mendelutesting.logic

import android.content.Context
import androidx.annotation.StringRes
import com.strv.mendelutesting.R

enum class TemperatureUnitsEnum(
    @StringRes val label: Int,
    @StringRes val formatValue: Int
) {
    CELSIUS(
        label = R.string.unit_of_temperature_celsius,
        formatValue = R.string.unit_of_temperature_celsius_value
    ),
    KELVIN(
        label = R.string.unit_of_temperature_kelvin,
        formatValue = R.string.unit_of_temperature_kelvin_value
    ),
    FAHRENHEIT(
        label = R.string.unit_of_temperature_fahrenheit,
        formatValue = R.string.unit_of_temperature_fahrenheit_value
    );

    companion object {
        private const val KELVIN_CELSIUS_DIFF = 273.15

        fun convertCelsiusToKelvin(celsius: Double): Double {
            return celsius + KELVIN_CELSIUS_DIFF
        }

        fun convertCelsiusToFahrenheit(celsius: Double): Double {
            //  TODO - some link?
            return (celsius * 9 / 5.0) + 32.0
        }
    }

    /**
     *  Convert base celsius temperature to selected unit system and format to string value with corresponding symbol.
     *  @param context application context
     *  @param valueCelsius value in base unit - Celsius
     *
     *  @return "30.0 °C" for Celsius
     *  @return "86.0 °F" for Fahrenheit
     *  @return "303.2 K" for Kelvin
     * */
    fun formatTemperature(context: Context, valueCelsius: Double): String {
        return when (this) {
            KELVIN -> context.getString(formatValue, convertCelsiusToKelvin(valueCelsius))
            FAHRENHEIT -> context.getString(formatValue, convertCelsiusToFahrenheit(valueCelsius))
            CELSIUS -> context.getString(formatValue, valueCelsius)
        }
    }
}