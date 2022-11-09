package com.strv.mendelutesting.ui.report

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.strv.mendelutesting.data.ReportWeatherType
import com.strv.mendelutesting.data.WeatherType
import com.strv.mendelutesting.extension.isValidEmail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor() : ViewModel() {

	private val _state: MutableStateFlow<ReportUiState> =
		MutableStateFlow(
			ReportUiState(
				emailValue = TextFieldValue(),
				descriptionValue = TextFieldValue(),
				showErrorEmail = false,
				reportWeatherTypes = createReportWeatherTypes(),
				validation = null
			)
		)
	val state = _state.asStateFlow()

	fun updateEmail(value: TextFieldValue) {
		_state.update { uiState ->
			uiState.copy(
				emailValue = value,
				showErrorEmail = value.text.isValidEmail().not()
			)
		}
	}

	fun updateDescription(value: TextFieldValue) {
		_state.update { uiState ->
			uiState.copy(descriptionValue = value)
		}
	}

	fun updateReportWeatherType(newReportWeatherType: ReportWeatherType) {
		_state.update { uiState ->
			uiState.copy(
				reportWeatherTypes = uiState.reportWeatherTypes.map {
					it.copy(
						isSelected = it.weatherType == newReportWeatherType.weatherType,
						weatherType = it.weatherType
					)
				}
			)
		}
	}

	fun sendReport() {
		//	TODO - evaluate if button is enabled or not
		val state = _state.value
		val selectedWeatherType = state.reportWeatherTypes.firstOrNull { it.isSelected }?.weatherType

		val isValid = when (selectedWeatherType) {
			WeatherType.RAINING_FISH_FROGS ->  false
			else -> true
		}

		_state.update { uiState ->
			uiState.copy(validation = isValid)
		}
	}

	fun clearNavigationFlags() {
		_state.update { uiState ->
			uiState.copy(validation = null)
		}
	}

	private fun createReportWeatherTypes(): List<ReportWeatherType> {
		return listOf(
			ReportWeatherType(isSelected = true, weatherType = WeatherType.NOT_SELECTED),
			ReportWeatherType(isSelected = false, weatherType = WeatherType.SUNNY),
			ReportWeatherType(isSelected = false, weatherType = WeatherType.OVERCAST),
			ReportWeatherType(isSelected = false, weatherType = WeatherType.RAINING),
			ReportWeatherType(isSelected = false, weatherType = WeatherType.STORM),
			ReportWeatherType(isSelected = false, weatherType = WeatherType.SNOWING),
			ReportWeatherType(isSelected = false, weatherType = WeatherType.RAINING_FISH_FROGS)
		)
	}
}