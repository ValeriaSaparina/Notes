package com.example.notes.ui.navigation

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.NavHostController
import com.example.notes.HomeScreen
import com.example.notes.resource.AndroidResourceManager
import com.example.notes.ui.base.component.folder.FoldersContract.Effect
import com.example.notes.ui.base.component.folder.FoldersScreen
import com.example.notes.ui.base.component.folder.FoldersViewModel

@Composable
fun FoldersScreenDestination(navController: NavHostController, context: Context) {
    val viewModel = remember { FoldersViewModel(AndroidResourceManager(context)) }
    FoldersScreen(
        state = viewModel.viewState.value,
        effectFlow = viewModel.effect,
        onEventSent = { event -> viewModel.setEvent(event) },
        onNavigationRequested = { effect ->

            when (effect) {
                is Effect.Navigation.ToNewNote -> navController.navigateToNote(
                    effect.folderId,
                    effect.noteId
                )

                is Effect.Navigation.ToNotes -> navController.navigateToNotes(effect.folderId)
            }
        }
    )
}

@Composable
fun HomeScreenDestination(navController: NavHostController) {
    HomeScreen {
        navController.navigateToFoldersList()
    }
}