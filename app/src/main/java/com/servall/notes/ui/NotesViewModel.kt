package com.servall.notes.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.servall.notes.data.NotesRemoteRepository
import com.servall.notes.entities.Note
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class NotesViewModel(
    private val notesRemoteRepository: NotesRemoteRepository = NotesRemoteRepository()
) : ViewModel() {

    private val _notesState = MutableStateFlow<NotesUiState>(NotesUiState.Loading)
    val notesState: StateFlow<NotesUiState>
        get() = _notesState

}

sealed class NotesUiState {
    object Loading : NotesUiState()
    data class Data(val notes: List<Note>) : NotesUiState()
    data class Error(val message: String) : NotesUiState()
}