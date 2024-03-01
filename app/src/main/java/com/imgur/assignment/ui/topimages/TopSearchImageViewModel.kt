package com.imgur.assignment.ui.topimages

import android.text.Editable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.assignment.data.model.Data
import com.imgur.assignment.data.repository.TopSearchImageRepository
import com.imgur.assignment.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopSearchImageViewModel(private val topSearchImageRepository: TopSearchImageRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Data>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Data>>> = _uiState

    fun fetchTopWeeklyImages(imageSearch: Editable?) {
        viewModelScope.launch {
            topSearchImageRepository.getTopSearchImages(imageSearch.toString())
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}