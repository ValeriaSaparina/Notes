package com.example.notes.impl.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.designsystem.BaseViewModel
import com.example.notes.api.usecase.CreateNoteUseCase
import com.example.notes.api.usecase.GetNotesByFolderIdUseCase
import com.example.notes.impl.mapper.toDomain
import com.example.notes.impl.mapper.toUi
import com.example.notes.impl.presentation.model.NoteItemUiModel
import com.example.notes.impl.presentation.model.NotesContract.Effect
import com.example.notes.impl.presentation.model.NotesContract.Event
import com.example.notes.impl.presentation.model.NotesContract.UiState
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotesByFolderIdUseCase: GetNotesByFolderIdUseCase,
    private val createNoteUseCase: CreateNoteUseCase,
    private val folderId: Long
) :
    BaseViewModel<Event, UiState, Effect>() {

        init {
            getNotesData(folderId)
        }

    override fun setInitialState(): UiState {
        return UiState(notesList = null, isLoading = false, isError = false)
    }

    override fun handleEvents(event: Event) {
        Log.d("NOTES_SCREEN_TAG", "HANDLE ${event}")
        when (event) {
            is Event.GetData -> getNotesData(folderId)
            Event.OnBackClicked -> Effect.Navigation.ToPrevious
            is Event.OnCreateNewNoteClicked -> createNewNote(
                event.note,
                event.folderId,
                event.isSync
            )

            is Event.OnNoteClicked -> Effect.Navigation.ToNote(event.folderId, event.noteId)
            is Event.Retry -> getNotesData(folderId)
            Event.Empty -> {}
        }
    }

    private fun createNewNote(
        note: NoteItemUiModel,
        folderId: Long,
        isSync: Boolean = false /* TODO: isSync */
    ) {
        viewModelScope.launch {
            createNoteUseCase.invoke(note.toDomain(), isSync)
                .onSuccess {
                    setEffect { Effect.Navigation.ToNote(folderId, note.id) }
                    setState { copy(isLoading = false, isError = false) }
                }
                .onFailure {
                    setEffect { Effect.NoteCreatingError(it.message ?: "Something went wrong") }
                    setState { copy(isLoading = false, isError = true) }
                }
        }
    }

    private fun getNotesData(folderId: Long) {

        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }
            getNotesByFolderIdUseCase.invoke(folderId)
                .onSuccess { list ->
                    setState {
                        copy(
                            notesList = NoteItemUiModel.getDefaultList(),
                            isLoading = false,
                            isError = false
                        )
                    }
                    setEffect { Effect.DataWasLoaded }

                }
                .onFailure { thr ->
                    // TODO: сделать нормальную обработку ошибок
                    setState { copy(isLoading = false, isError = true) }
                    setEffect { Effect.DataLoadingError(thr.message ?: "Something went wrong") }
                }
        }
    }
}