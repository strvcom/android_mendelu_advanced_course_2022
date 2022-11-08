package com.strv.mendelutesting.ui.report

import androidx.compose.ui.text.input.TextFieldValue
import com.strv.mendelutesting.data.CurrentWeather

data class ReportUiState(
	val emailValue: TextFieldValue,
	val showError: Boolean,
)
