package com.example.notes.impl.presentation

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.designsystem.BaseViewModel
import com.example.notes.api.model.NoteItemUiModel
import com.example.notes.api.usecase.CreateNoteUseCase
import com.example.notes.api.usecase.GetNotesByFolderIdUseCase
import com.example.notes.api.mapper.toDomain
import com.example.notes.api.mapper.toUi
import com.example.notes.impl.presentation.model.NotesContract.Effect
import com.example.notes.impl.presentation.model.NotesContract.Event
import com.example.notes.impl.presentation.model.NotesContract.UiState
import com.example.utils.resource.TimeUtil
import kotlinx.coroutines.launch

class NotesViewModel(
    private val getNotesByFolderIdUseCase: GetNotesByFolderIdUseCase,
    private val createNoteUseCase: CreateNoteUseCase,
    private val folderId: Long,
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
            Event.OnBackClicked -> setEffect {
                Effect.Navigation.ToPrevious
            }

            is Event.OnCreateNewNoteClicked -> createNewNote(
                event.note
            )

            is Event.OnNoteClicked -> setEffect {
                Effect.Navigation.ToNote(event.folderId, event.noteId)
            }

            is Event.Retry -> getNotesData(folderId)
            Event.Empty -> {}
        }
    }

    private fun createNewNote(
        note: NoteItemUiModel,
        isSync: Boolean = false, /* TODO: isSync */
    ) {
        viewModelScope.launch {
            val currentTime = TimeUtil.currentTimeUtc()
            createNoteUseCase.invoke(
                note.copy(folder = note.folder.copy(id = folderId)).toDomain().copy(
                    createDate = currentTime,
                    editDate = currentTime
                ),
                isSync
            )
                .onSuccess { noteId ->
                    setEffect { Effect.Navigation.ToNote(folderId, noteId) }
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
                            notesList = list.toUi(),
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