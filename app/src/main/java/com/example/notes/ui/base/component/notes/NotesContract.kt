package com.example.notes.ui.base.component.notes

import com.example.notes.ui.base.data.NoteItemUiModel
import com.example.notes.ui.base.utils.ViewEvent
import com.example.notes.ui.base.utils.ViewSideEffect
import com.example.notes.ui.base.utils.ViewState

class NotesContract {

    sealed class Event : ViewEvent {
        data class GetData(val folderId: String) : Event()
        data object Retry : Event()
        data object OnBackClicked : Event()
        data class OnCreateNewNoteClicked(val folderId: String, val noteId: String) : Event()
        data class OnNoteClicked(val folderId: String, val noteId: String) : Event()
    }

    data class UiState(
        val notesList: List<NoteItemUiModel>,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data object DataWasLoaded : Effect()
        sealed class Navigation : Effect() {
            data class ToNote(val folderId: String, val noteId: String) : Navigation()
            data object ToPrevious : Navigation()
        }
    }

}