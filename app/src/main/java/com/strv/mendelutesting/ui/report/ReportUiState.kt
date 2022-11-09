package com.strv.mendelutesting.ui.report

import androidx.compose.ui.text.input.TextFieldValue
import com.strv.mendelutesting.data.ReportWeatherType

data class ReportUiState(
    val emailValue: TextFieldValue,
    val descriptionValue: TextFieldValue,
    val showErrorEmail: Boolean,
    val reportWeatherTypes: List<ReportWeatherType>,
    val validation: Boolean? = null
)
