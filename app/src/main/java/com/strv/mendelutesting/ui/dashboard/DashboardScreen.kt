package com.strv.mendelutesting.ui.dashboard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.strv.mendelutesting.R
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
        //  TODO - convert units and nicer UI
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            uiState.currentWeather?.let {
                Text(
                    text = it.city,
                    style = MaterialTheme.typography.h2
                )
                Text(
                    text = stringResource(id = R.string.dashboard_temperature, it.temperature),
                    style = MaterialTheme.typography.h4
                )
                Text(
                    text = stringResource(id = R.string.dashboard_wind, it.windDirection),
                    style = MaterialTheme.typography.h4
                )
            }
            Spacer(modifier = Modifier.weight(1f))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = onReportClick
            ) {
                Text(text = stringResource(id = R.string.dashboard_report))
            }
        }
    }
}

@Preview
@Composable
private fun DashboardScreenPreview() {
    DashboardScreen(
        onReportClick = {}
    )
}
