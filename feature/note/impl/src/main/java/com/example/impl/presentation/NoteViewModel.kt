package com.example.impl.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.api.usecase.GetNoteByIdUseCase
import com.example.api.usecase.UpdateNoteUseCase
import com.example.designsystem.BaseViewModel
import com.example.impl.presentation.model.NoteContract.Effect
import com.example.impl.presentation.model.NoteContract.Event
import com.example.impl.presentation.model.NoteContract.UiState
import com.example.notes.api.mapper.toDomain
import com.example.notes.api.mapper.toUi
import com.example.notes.api.model.FolderModel
import com.example.notes.api.model.NoteItemUiModel
import kotlinx.coroutines.launch

class NoteViewModel(
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val folderId: Long,
    private val noteId: Long
) : BaseViewModel<Event, UiState, Effect>() {

    init {
        getNote()
    }

    override fun setInitialState(): UiState {
        return UiState(
            note = NoteItemUiModel(
                id = 0,
                title = "",
                text = "",
                createDate = "",
                editDate = "",
                folder = FolderModel(id = 0, name = "")
            ),
            isLoading = false,
            isError = false
        )
    }

    override fun handleEvents(event: Event) {
        when (event) {
            Event.Empty -> TODO()
            Event.GetNote -> TODO()
            Event.OnBackClicked -> setEffect { Effect.Navigation.ToPrevious }
            Event.Retry -> TODO()
            is Event.SaveNote -> updateNote(event.note)
        }
    }

    private fun updateNote(note: NoteItemUiModel) {
        viewModelScope.launch {
//            setState { copy(isLoading = true, isError = false) }
            updateNoteUseCase.invoke(note.toDomain())
                .onSuccess {

                }
                .onFailure {thr ->
                    Log.d("NOTE SCREEN", "SAVE FAILED: ${thr.message}")
                }
        }
    }

    private fun getNote() {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }
            getNoteByIdUseCase.invoke(noteId)
                .onSuccess {note ->
                    Log.d("NOTE SCREEN", note.text)
                    setState { copy(note = note.toUi(), isLoading = false) }
                    setEffect { Effect.NoteWasLoaded }
                }
                .onFailure {
                    setState { copy(isLoading = false, isError = true) }
                    setEffect { Effect.DataLoadingError(it.message.orEmpty()) }
                }
        }
    }
}