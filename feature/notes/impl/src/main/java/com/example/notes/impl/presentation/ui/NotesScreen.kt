package com.example.notes.impl.presentation.ui

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.designsystem.SIDE_EFFECTS_KEY
import com.example.designsystem.component.NetworkError
import com.example.designsystem.component.Progress
import com.example.notes.impl.presentation.model.NoteItemUiModel
import com.example.notes.impl.presentation.model.NotesContract.Effect
import com.example.notes.impl.presentation.model.NotesContract.Event
import com.example.notes.impl.presentation.model.NotesContract.UiState
import com.example.notes.impl.presentation.ui.component.NoteItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun NotesScreen(
    state: UiState,
    effectFlow: Flow<Effect>?,
    onEventSent: (event: Event) -> Unit,
    onNavigationRequested: (navigationEffect: Effect.Navigation) -> Unit
) {

    Scaffold(
        topBar = {},
        bottomBar = {}
    ) { paddingValues ->

        LaunchedEffect(key1 = SIDE_EFFECTS_KEY) {
            effectFlow?.onEach { effect ->
                when (effect) {
                    Effect.DataWasLoaded -> Log.d(NOTES_SCREEN_TAG, "Notes was loaded")
                    is Effect.Navigation.ToNote -> onNavigationRequested(effect)
                    is Effect.Navigation.ToPrevious -> onNavigationRequested(effect)
                    is Effect.DataLoadingError -> Log.d(NOTES_SCREEN_TAG, effect.message)
                    is Effect.NoteCreatingError -> Log.d(NOTES_SCREEN_TAG, effect.message)
                }
            }?.collect()
        }

        when {
            state.isLoading -> {
                Log.d(NOTES_SCREEN_TAG, "IS LOADING")
                Progress()
            }
            state.isError -> {
                Log.d(NOTES_SCREEN_TAG, "IS ERROR")
                NetworkError { onEventSent(Event.Retry) }
            }
            else -> {
                Log.d(NOTES_SCREEN_TAG, "IS SHOWING")
                NotesContent(paddingValues, state.notesList.orEmpty())
            }
        }

    }

}

@Composable
fun NotesContent(paddingValues: PaddingValues, notes: List<NoteItemUiModel>) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        itemsIndexed(items = notes, key = { _, note -> note.id }) { index, note ->
            NoteItem(data = note, isDivider = notes.lastIndex != index)
        }
    }
}

const val NOTES_SCREEN_TAG = "NOTES_SCREEN_TAG"