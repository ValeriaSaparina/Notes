package com.example.notes.ui.base.component.folder

import com.example.notes.ui.base.data.SectionData
import com.example.notes.ui.base.utils.ViewEvent
import com.example.notes.ui.base.utils.ViewSideEffect
import com.example.notes.ui.base.utils.ViewState

class FoldersContract {

    sealed class Event : ViewEvent {
        data object GetData : Event()
        data object Retry : Event()
        data class OnCreateNewFolderClicked(val folderName: String) : Event()
        data class OnCreateNewNoteClicked(val folderId: String, val noteId: String) : Event()
        data class OnFolderClicked(val folderId: String) : Event()
    }

    data class UiState(
        val sectionData: List<SectionData>,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data object DataWasLoaded : Effect()
        data object FolderWasCreated : Effect()
        sealed class Navigation : Effect() {
            data class ToNewNote(val folderId: String, val noteId: String) : Navigation()
            data class ToNotes(val folderId: String) : Navigation()
        }
    }

}