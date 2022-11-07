package com.strv.mendelutesting.ui.dashboard.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.strv.mendelutesting.R
import com.strv.mendelutesting.data.CurrentWeather
import com.strv.mendelutesting.logic.TemperatureUnitsEnum
import com.strv.mendelutesting.logic.WindDirectionsEnum

@Composable
fun WeatherInfo(
    currentWeather: CurrentWeather?,
    temperatureUnit: TemperatureUnitsEnum
) {
    if (currentWeather == null) return

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(24.dp)
    ) {
        //  City info
        Text(
            text = currentWeather.city.uppercase(),
            style = MaterialTheme.typography.h2.copy(fontWeight = FontWeight.Bold)
        )

        //  Temperature info
        val temperature = temperatureUnit.formatTemperature(
            valueCelsius = currentWeather.temperature
        )
        Text(
            text = stringResource(id = R.string.dashboard_temperature, temperature),
            style = MaterialTheme.typography.h5
        )

        //  Wind direction info
        val windDirection = stringResource(
            id = WindDirectionsEnum.fromDegrees(currentWeather.windDirection).label
        )
        Text(
            text = stringResource(id = R.string.dashboard_wind, windDirection),
            style = MaterialTheme.typography.h5
        )
    }
}
