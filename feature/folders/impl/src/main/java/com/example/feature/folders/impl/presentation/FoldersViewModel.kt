package com.example.feature.folders.impl.presentation

import androidx.lifecycle.viewModelScope
import com.example.designsystem.BaseViewModel
import com.example.designsystem.R
import com.example.feature.folders.api.usecase.CreateFolderUseCase
import com.example.feature.folders.api.usecase.GetAllFoldersUseCase
import com.example.feature.folders.impl.mapper.toUi
import com.example.feature.folders.impl.presentation.model.FolderUiModel
import com.example.feature.folders.impl.presentation.model.FoldersContract.Effect
import com.example.feature.folders.impl.presentation.model.FoldersContract.Event
import com.example.feature.folders.impl.presentation.model.FoldersContract.UiState
import kotlinx.coroutines.launch

class FoldersViewModel(
    private val getAllFoldersUseCase: GetAllFoldersUseCase,
    private val createFolderUseCase: CreateFolderUseCase
) : BaseViewModel<Event, UiState, Effect>() {

    init {
        getFoldersData()
    }

    override fun setInitialState(): UiState {
        return UiState(
            sectionData = listOf(), isLoading = false, isError = false
        )
    }

    override fun handleEvents(event: Event) {
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
            Event.Empty -> setEffect { Effect.Empty }
        }
    }

    private fun createNewFolder(folderName: String) {

        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }
            createFolderUseCase.invoke(folderName)
                .onSuccess { id ->
                    setState {
                        copy(
                            sectionData = sectionData.apply {
                                find { it.headerId == R.string.local_name_folder }!!.items.apply {
                                    add(
                                        FolderUiModel(
                                            id = id,
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
                .onFailure { thr ->
                    setState { copy(isLoading = false, isError = true) }
                    setEffect { Effect.FolderCreateError(thr.message.orEmpty()) }
                }
        }


    }

    private fun getFoldersData() {
        viewModelScope.launch {
            setState { copy(isLoading = true, isError = false) }
            getAllFoldersUseCase.invoke()
                .onSuccess { list ->
                    setState {
                        copy(
                            sectionData = list.toUi(),
                            isLoading = false,
                            isError = false
                        )
                    }
                    setEffect { Effect.DataWasLoaded }
                }
                .onFailure { thr ->
                    setState {
                        copy(isLoading = false, isError = true)
                    }
                    setEffect { Effect.DataLoadingError(thr.message.orEmpty()) }
                }
        }


    }

}