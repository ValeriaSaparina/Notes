package com.example.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.notes.ui.base.component.folder.ExpandableList
import com.example.notes.ui.base.data.FolderItemUiModel
import com.example.notes.ui.base.data.NoteItemUiModel
import com.example.notes.ui.base.data.SectionData
import com.example.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = Color(0x99F3F2F8)
                ) {
                    ExpandableList(
                        listOf(
                            SectionData(
                                "Folder",
                                items = listOf(
                                    FolderItemUiModel.getDefault(),
                                    FolderItemUiModel.getDefault(),
                                    FolderItemUiModel.getDefault()
                                )
                            ),
                            SectionData(
                                "Notes",
                                items = listOf(
                                    NoteItemUiModel.getDefault(),
                                    NoteItemUiModel.getDefault(),
                                    NoteItemUiModel.getDefault()
                                )
                            ),
                        )
                    )
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    NotesTheme {
        Greeting("Android")
    }
}