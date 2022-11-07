package com.strv.mendelutesting.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.strv.mendelutesting.R
import com.strv.mendelutesting.data.CurrentWeather
import com.strv.mendelutesting.logic.TemperatureUnitsEnum
import com.strv.mendelutesting.logic.WindDirectionsEnum
import com.strv.mendelutesting.ui.dashboard.components.Compass
import com.strv.mendelutesting.ui.dashboard.components.TemperatureUnitsSelection
import timber.log.Timber

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    onReportClick: () -> Unit
) {
    val uiState by viewModel.state.collectAsState()
    Timber.d("DashboardScreen uiState = $uiState")

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            CurrentWeatherData(
                currentWeather = uiState.currentWeather,
                temperatureUnit = uiState.temperatureUnit
            )
            Divider()

            Compass()

            Divider()

            TemperatureUnitsSelection(
                selectedUnit = uiState.temperatureUnit,
                onUnitChange = viewModel::onTemperatureUnitChange
            )

            Divider()

            Spacer(modifier = Modifier.weight(1f))
            ReportWeather(onReportClick = onReportClick)
        }
    }
}

@Composable
private fun CurrentWeatherData(
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

@Composable
private fun ReportWeather(onReportClick: () -> Unit) {
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = onReportClick
    ) {
        Text(text = stringResource(id = R.string.dashboard_report))
    }
}

@Preview
@Composable
private fun DashboardScreen_Preview() {
    DashboardScreen(
        onReportClick = {}
    )
}
