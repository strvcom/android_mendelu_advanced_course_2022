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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.strv.mendelutesting.R

val TEST_TAG_REPORT_EMAIL_INPUT = "email_input"

@Composable
fun ReportScreen(
	viewModel: ReportViewModel = hiltViewModel(),
) {
	val state by viewModel.state.collectAsState()
	var expanded by remember { mutableStateOf(false) }

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
			TextField(
				onValueChange = viewModel::updateEmailValue,
				value = state.emailValue,
				maxLines = 1,
				placeholder = { Text(stringResource(R.string.enter_email_hint)) },
				modifier = Modifier
					.fillMaxWidth()
					.padding(8.dp)
					.testTag(TEST_TAG_REPORT_EMAIL_INPUT)
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
			Text(
				text = "Weather type:",
				style = MaterialTheme.typography.h5,
				modifier = Modifier
					.fillMaxWidth()
					.padding(horizontal = 12.dp)
			)
			Box {
				Row(
					Modifier
						.align(Alignment.Center)
						.clickable { expanded = true }
						.padding(horizontal = 12.dp)
				) {
					Text(
						text = state.reportWeatherTypes.firstOrNull { it.selected }?.weatherConditionName
							?: "No weather?",
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
					state.reportWeatherTypes.forEach { weatherType ->
						DropdownMenuItem(
							modifier = Modifier
								.fillMaxWidth()
								.padding(horizontal = 12.dp),
							onClick = {
								viewModel.selectWeatherType(weatherType)
								expanded = false
							},
						) {
							Box() {
								Text(
									text = weatherType.weatherConditionName,
									modifier = Modifier.wrapContentWidth()
								)
							}

						}
					}
				}
			}
			Spacer(modifier = Modifier.weight(weight = 1f, fill = true))
			Button(
				modifier = Modifier.fillMaxWidth(),
				onClick = viewModel::sendReport
			) {
				Text(text = stringResource(id = R.string.send_report))
			}
		}
	}
}

@Preview
@Composable
private fun ReportScreenPreview() {
	ReportScreen()
}
