package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.example.navigation.NavigationApp
import com.example.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            NotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0x51F3F2F8)
                ) {

                    NavigationApp()

//                    FoldersScreen(sectionData = SectionData.getDefaultList())
//                    NewFolderDialog()


//                    NoteScreen(note = NoteItemUiModel.getDefault())
//                    MarkdownSampleTwo()
//                    ExpandableList(
//                        listOf(
//                            SectionData(
//                                "Folder",
//                                items = listOf(
//                                    FolderItemUiModel.getDefault(),
//                                    FolderItemUiModel.getDefault(),
//                                    FolderItemUiModel.getDefault()
//                                )
//                            ),
//                            SectionData(
//                                "Notes",
//                                items = listOf(
//                                    NoteItemUiModel.getDefault(),
//                                    NoteItemUiModel.getDefault(),
//                                    NoteItemUiModel.getDefault()
//                                )
//                            ),
//                        )
//                    )
                }
            }
        }
    }

}

@Composable
fun HomeScreen(navigateToFolders: () -> Unit) {
    Button(
        onClick = {
            navigateToFolders()
        },
    ) {
        Text("Hello!")
    }
}

//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//    NotesTheme {
//        Greeting("Android")
//    }
//}

