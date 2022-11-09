package com.strv.mendelutesting.ui.report

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.strv.mendelutesting.R
import com.strv.mendelutesting.data.ReportWeatherType

const val TEST_TAG_REPORT_BUTTON = "report_button"
const val TEST_TAG_REPORT_DESCRIPTION_INPUT = "description_input"
const val TEST_TAG_REPORT_EMAIL_INPUT = "email_input"
const val TEST_TAG_REPORT_EMAIL_ERROR = "email_error"

@Composable
fun ReportScreen(
    viewModel: ReportViewModel = hiltViewModel(),
    navigateToSuccessScreen: () -> Unit,
    navigateToFailScreen: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    LaunchedEffect(state.validation) {
        when (state.validation) {
            true -> navigateToSuccessScreen()
            false -> navigateToFailScreen()
            else -> Unit
        }
        viewModel.clearNavigationFlags()
    }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colors.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(32.dp)
        ) {
            InputEmail(
                email = state.emailValue,
                showError = state.showErrorEmail,
                updateEmail = viewModel::updateEmail
            )
            DropdownWeatherTypes(
                reportWeatherTypes = state.reportWeatherTypes,
                updateReportWeatherType = viewModel::updateReportWeatherType
            )
            InputDescription(
                description = state.descriptionValue,
                updateDescription = viewModel::updateDescription
            )
            Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
            ButtonSendReport(onSendReportClick = viewModel::sendReport)
        }
    }
}

@Composable
private fun InputEmail(
    email: TextFieldValue,
    showError: Boolean,
    updateEmail: (TextFieldValue) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.report_enter_email_title),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        TextField(
            onValueChange = updateEmail,
            value = email,
            maxLines = 1,
            placeholder = { Text(stringResource(R.string.report_enter_email_hint)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag(TEST_TAG_REPORT_EMAIL_INPUT)
        )
        AnimatedVisibility(
            visible = showError,
            enter = fadeIn(),
            exit = fadeOut()
        ) {
            Text(
                text = stringResource(id = R.string.report_enter_email_invalid),
                color = MaterialTheme.colors.error,
                modifier = Modifier.testTag(TEST_TAG_REPORT_EMAIL_ERROR)
            )
        }
    }
}

@Composable
private fun DropdownWeatherTypes(
    reportWeatherTypes: List<ReportWeatherType>,
    updateReportWeatherType: (ReportWeatherType) -> Unit
) {
    val selectedWeatherType = reportWeatherTypes.firstOrNull { it.isSelected }?.weatherType
    var expanded by remember { mutableStateOf(false) }

    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.report_select_weather_title),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        Box {
            Row(
                Modifier
                    .align(Alignment.Center)
                    .clickable { expanded = true }
                    .padding(horizontal = 12.dp)
            ) {
                Text(
                    text = selectedWeatherType?.displayName ?: "No weather?",
                    modifier = Modifier
                        .weight(1f, fill = true)
                        .padding(vertical = 12.dp)
                        .align(Alignment.CenterVertically)
                )
                Icon(
                    painter = painterResource(id = dagger.android.support.R.drawable.abc_ic_arrow_drop_right_black_24dp),
                    tint = MaterialTheme.colors.onBackground,
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(90f)
                        .align(Alignment.CenterVertically)
                )
            }
            DropdownMenu(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                reportWeatherTypes.forEach { weatherType ->
                    DropdownMenuItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 12.dp),
                        onClick = {
                            updateReportWeatherType(weatherType)
                            expanded = false
                        },
                    ) {
                        Box() {
                            Text(
                                text = weatherType.weatherType.displayName,
                                modifier = Modifier.wrapContentWidth()
                            )
                        }

                    }
                }
            }
        }
    }
}

@Composable
private fun InputDescription(
    description: TextFieldValue,
    updateDescription: (TextFieldValue) -> Unit
) {
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = stringResource(R.string.report_enter_description_title),
            style = MaterialTheme.typography.h5,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 4.dp)
        )
        TextField(
            onValueChange = updateDescription,
            value = description,
            placeholder = { Text(stringResource(R.string.report_enter_description_hint)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .testTag(TEST_TAG_REPORT_DESCRIPTION_INPUT)
        )
    }
}


@Composable
private fun ButtonSendReport(onSendReportClick: () -> Unit) {
    Button(
        modifier = Modifier
            .fillMaxWidth()
            .testTag(TEST_TAG_REPORT_BUTTON),
        onClick = onSendReportClick
    ) {
        Text(text = stringResource(id = R.string.report_send_report_button))
    }
}

@Preview
@Composable
private fun ReportScreenPreview() {
    ReportScreen(navigateToFailScreen = {}, navigateToSuccessScreen = {})
}
