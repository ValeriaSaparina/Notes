package com.example.impl.presentation.ui

import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.focusable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEventType
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.input.key.type
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.designsystem.SIDE_EFFECTS_KEY
import com.example.designsystem.component.NetworkError
import com.example.designsystem.component.Progress
import com.example.impl.presentation.model.NoteContract.Effect
import com.example.impl.presentation.model.NoteContract.Event
import com.example.impl.presentation.model.NoteContract.UiState
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.onEach

@OptIn(FlowPreview::class)
@Composable
fun NoteScreen(
    state: UiState,
    effectFlow: Flow<Effect>?,
    onEventSent: (event: Event) -> Unit,
    onNavigationRequested: (navigationEffect: Effect.Navigation) -> Unit,
) {

    val focusRequester = remember { FocusRequester() }
    val focusManager = LocalFocusManager.current
    val noteTitle = remember {
        mutableStateOf(state.note.title)
    }
    val noteTitleFlow = remember {
        MutableStateFlow(noteTitle.value)
    }
    val noteText = remember {
        mutableStateOf(state.note.text)
    }
    val noteTextFlow = remember {
        MutableStateFlow(noteText.value)
    }

    BackHandler {
        onEventSent(
            Event.SaveNote(
                state.note.copy(
                    title = noteTitleFlow.value,
                    text = noteTextFlow.value
                )
            )
        )
        onEventSent(Event.OnBackClicked)
    }

    LaunchedEffect(state.note) {
        noteTitle.value = state.note.title
        noteText.value = state.note.text
        noteTitleFlow.value = state.note.title
        noteTextFlow.value = state.note.text
    }

    LaunchedEffect(key1 = noteText.value) {
        noteTextFlow.debounce(5000).collectLatest { text ->
            onEventSent(Event.SaveNote(state.note.copy(text = text)))
        }
    }

    LaunchedEffect(key1 = noteTitle.value) {
        noteTitleFlow.debounce(5000).collectLatest { title ->
            onEventSent(Event.SaveNote(state.note.copy(title = title)))
        }
    }

    LaunchedEffect(key1 = SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                Effect.NoteWasLoaded -> {
                    Log.d("NOTE SCREEN", "NOTE WAS LOADED")
                }

                is Effect.DataLoadingError -> Log.d(NOTE_SCREEN_TAG, effect.message)
                Effect.NoteWasSave -> {
                    Log.d(NOTE_SCREEN_TAG, "note was loaded")
                }

                Effect.Navigation.ToPrevious -> onNavigationRequested(Effect.Navigation.ToPrevious)
            }
        }?.collect()
    }

    when {
        state.isLoading -> Progress()
        state.isError -> NetworkError { onEventSent(Event.Retry) }
        else -> {

            Column(modifier = Modifier.padding(16.dp)) {
                BasicTextField(
                    value = noteTitle.value,
                    onValueChange = {
                        noteTitle.value = it
                        noteTitleFlow.value = it
                    },
                    singleLine = true,
                    modifier = Modifier
                        .focusRequester(focusRequester)
                        .onKeyEvent {
                            if (it.key == Key.Enter && it.type == KeyEventType.KeyDown) {
                                focusManager.moveFocus(FocusDirection.Next)
                                true
                            } else {
                                false
                            }
                        }
                        .focusable(),
                    textStyle = TextStyle(fontWeight = FontWeight.Bold, fontSize = 24.sp),
                )
                BasicTextField(
                    value = noteText.value, onValueChange = {
                        noteText.value = it
                        noteTextFlow.value = it
                    }
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NoteScreenPreview() {
//    NoteScreen(note = NoteItemUiModel.getDefault())
}

private const val NOTE_SCREEN_TAG = "NOTE_SCREEN_TAG"