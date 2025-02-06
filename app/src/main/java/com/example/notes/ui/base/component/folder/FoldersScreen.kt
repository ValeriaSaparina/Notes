@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.notes.ui.base.component.folder

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.ui.base.component.common.NetworkError
import com.example.notes.ui.base.component.common.Progress
import com.example.notes.ui.base.component.folder.FoldersContract.Effect
import com.example.notes.ui.base.component.folder.FoldersContract.Event
import com.example.notes.ui.base.component.folder.FoldersContract.UiState
import com.example.notes.ui.base.utils.SIDE_EFFECTS_KEY
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach

@Composable
fun FoldersScreen(
    state: UiState,
    effectFlow: Flow<Effect>?,
    onEventSent: (event: Event) -> Unit,
    onNavigationRequested: (navigationEffect: Effect.Navigation) -> Unit,
) {

    Scaffold(
        modifier = Modifier.padding(horizontal = 16.dp),
        topBar = {
            CenterAlignedTopAppBar(title = { Text(text = "Folders") })
        },
        bottomBar = {
            BottomAppBar(containerColor = Color.Transparent) {
                var showBottomSheet by remember { mutableStateOf(false) }
                if (showBottomSheet) {
                    NewFolderDialog(onDismissRequest = {
                        showBottomSheet = false
                    }) {
                        onEventSent(Event.OnCreateNewFolderClicked(it))
                    }
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Image(
                        modifier = Modifier.clickable {
                            Log.d(FOLDER_SCREEN_TAG, "add folder clicked")
                            showBottomSheet = true
                        },
                        painter = painterResource(id = R.drawable.outline_create_new_folder_24),
                        contentDescription = ""
                    )
                    Image(
                        modifier = Modifier.clickable {
                            Log.d(FOLDER_SCREEN_TAG, "add note clicked")
                        },
                        painter = painterResource(id = R.drawable.outline_note_add_24),
                        contentDescription = ""
                    )
                }
            }
        }
    ) { paddingValue ->
        when {
            state.isLoading -> Progress()
            state.isError -> NetworkError { onEventSent(Event.Retry) }
            else -> ExpandableList(
                modifier = Modifier.padding(paddingValue),
                sectionData = state.sectionData,
            ) { folderId ->
                onEventSent(Event.OnFolderClicked(folderId))
            }
        }

    }


    LaunchedEffect(key1 = SIDE_EFFECTS_KEY) {
        effectFlow?.onEach { effect ->
            when (effect) {
                Effect.DataWasLoaded -> Log.d(FOLDER_SCREEN_TAG, "data was loaded")
                is Effect.Navigation.ToNewNote -> onNavigationRequested(effect)
                is Effect.Navigation.ToNotes -> onNavigationRequested(effect)
                Effect.FolderWasCreated -> Log.d(FOLDER_SCREEN_TAG, "folder was created")
            }
        }?.collect()
    }
}

//@Preview(showBackground = true)
//@Composable
//fun FoldersScreenPreview() {
//    NotesTheme {
////        FoldersScreen(sectionData = SectionData.getDefaultList())
//    }
//}

const val FOLDER_SCREEN_TAG = "FolderScreenTag"