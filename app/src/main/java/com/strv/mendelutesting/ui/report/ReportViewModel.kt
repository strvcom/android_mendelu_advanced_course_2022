package com.strv.mendelutesting.ui.report

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import com.strv.mendelutesting.data.ReportWeatherType
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
				showError = false,
				reportWeatherTypes = createReportWeatherTypes()
			)
		)
	val state = _state.asStateFlow()

	fun updateEmailValue(value: TextFieldValue) {
		_state.update {
			it.copy(emailValue = value, showError = value.text.isValidEmail().not())
		}
	}

	fun selectWeatherType(weatherType: ReportWeatherType) {
		_state.update {
			it.copy(reportWeatherTypes = it.reportWeatherTypes.map {
				it.copy(selected = it.id == weatherType.id)
			})
		}
	}

	fun sendReport() {
		// TODO add some actual repository which might need to be mocked?
	}

	private fun createReportWeatherTypes(): List<ReportWeatherType> {
		return listOf(
			ReportWeatherType(0, false, "Sunny"),
			ReportWeatherType(1, true, "Overcast"),
			ReportWeatherType(2, false, "Raining"),
			ReportWeatherType(3, false, "Storm"),
			ReportWeatherType(4, false, "Snowing"),
			ReportWeatherType(5, false, "Raining fish&frogs")
		)
	}
}