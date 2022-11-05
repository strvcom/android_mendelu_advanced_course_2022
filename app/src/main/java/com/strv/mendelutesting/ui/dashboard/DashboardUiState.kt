package com.strv.mendelutesting.ui.dashboard

import com.strv.mendelutesting.data.CurrentWeather

data class DashboardUiState(
    val isLoading: Boolean = false,
    val currentWeather: CurrentWeather? = null
)