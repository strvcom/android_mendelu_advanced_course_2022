package com.strv.mendelutesting.ui.report

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
import com.strv.mendelutesting.logic.AppScreens

@Composable
fun ReportScreen() {
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(
                text = stringResource(id = AppScreens.Report.name),
                modifier = Modifier.align(Alignment.Center),
                color = Color.Blue
            )
        }
    }
}

@Preview
@Composable
private fun ReportScreenPreview() {
    ReportScreen()
}
