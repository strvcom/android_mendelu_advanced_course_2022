package com.strv.mendelutesting.ui.dashboard

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.strv.mendelutesting.data.ServerMock
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DashboardViewModel @Inject constructor() : ViewModel() {

    private val _state: MutableStateFlow<DashboardUiState> =
        MutableStateFlow(DashboardUiState(isLoading = true))
    val state = _state.asStateFlow()

    init {
        viewModelScope.launch { fetchActualWeather() }
    }

    private suspend fun fetchActualWeather() {
        _state.update { dashboardUiState ->
            dashboardUiState.copy(isLoading = true)
        }

        val currentWeather = ServerMock.getCurrentWeather()

        _state.update { dashboardUiState ->
            dashboardUiState.copy(
                isLoading = false,
                currentWeather = currentWeather
            )
        }
    }
}