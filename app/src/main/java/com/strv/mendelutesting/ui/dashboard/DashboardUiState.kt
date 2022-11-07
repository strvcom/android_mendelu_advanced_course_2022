package com.strv.mendelutesting.ui.dashboard

import com.strv.mendelutesting.data.CurrentWeather
import com.strv.mendelutesting.logic.TemperatureUnitsEnum

data class DashboardUiState(
    val isLoading: Boolean = false,
    val temperatureUnit: TemperatureUnitsEnum = TemperatureUnitsEnum.CELSIUS,
    val currentWeather: CurrentWeather? = null
)