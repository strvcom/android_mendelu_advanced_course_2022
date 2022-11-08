package com.strv.mendelutesting.ui.report

import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strv.mendelutesting.data.ServerMock
import com.strv.mendelutesting.extension.isValidEmail
import com.strv.mendelutesting.ui.report.ReportUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ReportViewModel @Inject constructor() : ViewModel() {

	private val _state: MutableStateFlow<ReportUiState> =
		MutableStateFlow(ReportUiState(emailValue = TextFieldValue(), showError = false))
	val state = _state.asStateFlow()

	fun updateEmailValue(value: TextFieldValue) {
		_state.update {
			it.copy(emailValue = value, showError = value.text.isValidEmail().not())
		}
	}

	fun sendReport() {
		// TODO add some actual repository which might need to be mocked?
	}
}