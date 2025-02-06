package com.example.notes.ui.base.component.notes

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import com.example.notes.ui.base.component.common.NetworkError
import com.example.notes.ui.base.component.common.Progress
import com.example.notes.ui.base.component.folder.FOLDER_SCREEN_TAG
import com.example.notes.ui.base.component.folder.FoldersContract
import com.example.notes.ui.base.component.notes.NotesContract.*
import com.example.notes.ui.base.data.NoteItemUiModel
import com.example.notes.ui.base.utils.SIDE_EFFECTS_KEY
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

        when {
            state.isLoading -> Progress()
            state.isError -> {
                NetworkError { onEventSent(Event.Retry) }
            }
            else -> NotesContent(paddingValues, state.notesList)
        }

    }

    LaunchedEffect(key1 = SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                Effect.DataWasLoaded -> Log.d(NOTES_SCREEN_TAG, "Notes was loaded")
                is Effect.Navigation.ToNote -> onNavigationRequested(effect)
                is Effect.Navigation.ToPrevious -> onNavigationRequested(effect)
            }
        }?.collect()
    }

}

@Composable
fun NotesContent(paddingValues: PaddingValues, notes: List<NoteItemUiModel>) {
    LazyColumn(modifier = Modifier.padding(paddingValues)) {
        itemsIndexed(items = notes, key = {_, note -> note.id}) { index, note ->
            NoteItem(data = note, isDivider = notes.lastIndex != index)
        }
    }
}

const val NOTES_SCREEN_TAG = "NOTES_SCREEN_TAG"