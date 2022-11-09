package com.strv.mendelutesting.ui.report

import androidx.compose.ui.text.input.TextFieldValue
import com.strv.mendelutesting.data.ReportWeatherType
import com.strv.mendelutesting.data.WeatherType
import com.strv.mendelutesting.extension.isValidEmail

data class ReportUiState(
    val emailValue: TextFieldValue,
    val descriptionValue: TextFieldValue,
    val showErrorEmail: Boolean,
    val reportWeatherTypes: List<ReportWeatherType>,
    val validation: Boolean? = null
) {
    fun getSelectedWeatherType(): WeatherType? =
        reportWeatherTypes.firstOrNull { it.isSelected }?.weatherType

    val sendReportButtonEnabled: Boolean
        get() {
            val selectedWeatherType = getSelectedWeatherType()
            return emailValue.text.isValidEmail() && descriptionValue.text.isNotEmpty() && selectedWeatherType != WeatherType.NOT_SELECTED
        }
}
