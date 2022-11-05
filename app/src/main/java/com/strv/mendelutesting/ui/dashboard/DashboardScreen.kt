package com.strv.mendelutesting.ui.dashboard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.strv.mendelutesting.logic.navigation.AppScreens

@Composable
fun DashboardScreen(
    viewModel: DashboardViewModel = hiltViewModel(),
    onReportClick: () -> Unit
) {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                modifier = Modifier
                    .align(Alignment.Center)
                    .clickable { onReportClick() },
                text = stringResource(id = AppScreens.Dashboard.name),
                color = Color.Red,
            )
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
