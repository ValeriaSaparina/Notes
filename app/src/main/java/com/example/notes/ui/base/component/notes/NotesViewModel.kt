package com.example.notes.ui.base.component.notes

import com.example.notes.ui.base.component.notes.NotesContract.Effect
import com.example.notes.ui.base.component.notes.NotesContract.Event
import com.example.notes.ui.base.component.notes.NotesContract.UiState
import com.example.notes.ui.base.data.NoteItemUiModel
import com.example.notes.ui.base.utils.BaseViewModel

class NotesViewModel :
    BaseViewModel<Event, UiState, Effect>() {

//    init {
//        setEvent(Event.GetData(folderId))
//    }

    override fun setInitialState(): UiState {
        return UiState(notesList = listOf(), isLoading = false, isError = false)
    }

    override fun handleEvents(event: Event) {
        when (event) {
            is Event.GetData -> getNotesData(event.folderId)
            Event.OnBackClicked -> Effect.Navigation.ToPrevious
            is Event.OnCreateNewNoteClicked -> createNewNote(event.folderId)
            is Event.OnNoteClicked -> Effect.Navigation.ToNote(event.folderId, event.noteId)
            Event.Retry -> TODO()
        }
    }

    private fun createNewNote(folderId: String) {
        TODO("Not yet implemented")
    }

    private fun getNotesData(folderId: String) {

        setState { copy(isLoading = true, isError = false) }
        setState {
            copy(
                notesList = NoteItemUiModel.getDefaultList(),
                isLoading = false,
                isError = false
            )
        }
        setEffect { Effect.DataWasLoaded }

        return
    }
}