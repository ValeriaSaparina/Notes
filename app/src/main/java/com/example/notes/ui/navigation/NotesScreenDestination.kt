package com.example.notes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.notes.ui.base.component.notes.NotesContract.*
import com.example.notes.ui.base.component.notes.NotesScreen
import com.example.notes.ui.base.component.notes.NotesViewModel

@Composable
fun NotesScreenDestination(navController: NavHostController, folderId: String) {
    val viewModel = remember { NotesViewModel() }
    viewModel.setEvent(Event.GetData(folderId))
    NotesScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { effect ->
            when (effect) {
                is Effect.Navigation.ToNote -> navController.navigateToNote(
                    effect.folderId,
                    effect.noteId
                )

                Effect.Navigation.ToPrevious -> navController.popBackStack()
            }
        }
    )
}