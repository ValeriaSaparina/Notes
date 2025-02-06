package com.example.notes.ui.base.component.folder

import android.util.Log
import com.example.notes.R
import com.example.notes.resource.ResourceManager
import com.example.notes.ui.base.component.folder.FoldersContract.Effect
import com.example.notes.ui.base.component.folder.FoldersContract.Event
import com.example.notes.ui.base.component.folder.FoldersContract.UiState
import com.example.notes.ui.base.data.FolderItemUiModel
import com.example.notes.ui.base.data.SectionData
import com.example.notes.ui.base.utils.BaseViewModel

class FoldersViewModel(private val resourceManager: ResourceManager) :
    BaseViewModel<Event, UiState, Effect>() {

    private val counter = 7623

    init {
        getFoldersData()
    }

    override fun setInitialState(): UiState {
        return UiState(
            sectionData = listOf(), isLoading = false, isError = false
        )
    }

    override fun handleEvents(event: Event) {
        Log.d(FOLDER_SCREEN_TAG, "$event")
        when (event) {
            Event.GetData -> getFoldersData()
            is Event.OnFolderClicked -> setEffect {
                Effect.Navigation.ToNotes(event.folderId)
            }

            is Event.OnCreateNewNoteClicked -> setEffect {
                Effect.Navigation.ToNewNote(event.folderId, event.noteId)
            }

            is Event.OnCreateNewFolderClicked -> createNewFolder(event.folderName)
            Event.Retry -> getFoldersData()
        }
    }

    private fun createNewFolder(folderName: String) {
        setState { copy(isLoading = true, isError = false) }
        setState {
            // TODO: saveFolder and then take it from db to get id
            copy(
                sectionData = sectionData.apply {
                    find {
                        it.headerText == resourceManager.getString(
                            R.string.local_name_folder
                        )
                    }!!.items.apply {
                        add(
                            FolderItemUiModel(
                                id = "${counter}Fodler",
                                name = folderName,
                                notesNumber = 0
                            )
                        )
                    }
                },
                isLoading = false,
                isError = false
            )
        }
        setEffect { Effect.FolderWasCreated }
    }

    private fun getFoldersData() {
//        TODO: getFoldersData()

        setState { copy(isLoading = true, isError = false) }
        setState {
            copy(
                sectionData = SectionData.getDefaultList(),
                isLoading = false,
                isError = false
            )
        }
        setEffect { Effect.DataWasLoaded }
    }

}