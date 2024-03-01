package com.imgur.assignment.ui.topimages

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.imgur.assignment.data.model.Data
import com.imgur.assignment.data.repository.TopWeeklyImagesRepository
import com.imgur.assignment.ui.base.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class TopWeeklyImagesViewModel(private val topWeeklyImagesRepository: TopWeeklyImagesRepository) :
    ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<Data>>>(UiState.Loading)

    val uiState: StateFlow<UiState<List<Data>>> = _uiState

    init {
        fetchTopWeeklyImages()
    }

    private fun fetchTopWeeklyImages() {
        viewModelScope.launch {
            topWeeklyImagesRepository.getTopWeeklyImages()
                .catch { e ->
                    _uiState.value = UiState.Error(e.toString())
                }
                .collect {
                    _uiState.value = UiState.Success(it)
                }
        }
    }

}