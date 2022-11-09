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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.strv.mendelutesting.R
import com.strv.mendelutesting.ui.dashboard.components.*

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    onReportClick: () -> Unit
) {
    val uiState by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            WeatherStates(uiState = uiState)
            Divider()
            RadioGroupTemperatureUnits(
                selectedUnit = uiState.temperatureUnit,
                onUnitChange = viewModel::onTemperatureUnitChange
            )
            Spacer(modifier = Modifier.weight(1f))
            ButtonReportWeather(onReportClick = onReportClick)
        }
    }
}

@Composable
fun WeatherStates(uiState: DashboardUiState) {
    when {
        uiState.isLoading -> WeatherLoading()
        uiState.currentWeather == null -> WeatherError()
        else -> {
            WeatherContent(
                currentWeather = uiState.currentWeather,
                temperatureUnit = uiState.temperatureUnit
            )
        }
    }
}

@Composable
private fun ButtonReportWeather(onReportClick: () -> Unit) {
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
