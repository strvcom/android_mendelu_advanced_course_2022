package com.strv.mendelutesting.ui.report

import androidx.compose.ui.text.input.TextFieldValue
import com.strv.mendelutesting.data.ReportWeatherType

data class ReportUiState(
	val emailValue: TextFieldValue,
	val showError: Boolean,
	val reportWeatherTypes: List<ReportWeatherType>
)
