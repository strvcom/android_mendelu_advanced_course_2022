package com.strv.mendelutesting.ui.report

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.strv.mendelutesting.R

const val TEST_TAG_REPORT_BUTTON = "report_button"

@Composable
fun ReportScreen(
    viewModel: ReportViewModel = hiltViewModel(),
    onSendReportClick: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(24.dp)
        ) {
            BasicTextField(
                onValueChange = viewModel::updateEmailValue,
                value = state.emailValue,
                textStyle = TextStyle.Default.copy(color = MaterialTheme.colors.onBackground),
                cursorBrush = SolidColor(MaterialTheme.colors.onBackground),
                maxLines = 1,
                modifier = Modifier
                    .fillMaxWidth()
                    .background(MaterialTheme.colors.secondary)
                    .padding(8.dp)
                    .testTag("email")
            )
            AnimatedVisibility(
                visible = state.showError,
                enter = fadeIn(),
                exit = fadeOut()
            ) {
                Box {
                    Text(
                        text = stringResource(id = R.string.invalid_email),
                        color = MaterialTheme.colors.error
                    )
                }
            }
            Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
            Button(
                modifier = Modifier.fillMaxWidth().testTag(TEST_TAG_REPORT_BUTTON),
                onClick = { onSendReportClick() }
            ) {
                Text(text = stringResource(id = R.string.send_report))
            }
        }
    }
}

@Preview
@Composable
private fun ReportScreenPreview() {
    ReportScreen(onSendReportClick = {})
}
