package com.example.notes.ui.base.component.note

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.notes.ui.base.data.NoteItemUiModel

@Composable
fun NoteScreen(
    modifier: Modifier = Modifier,
    note: NoteItemUiModel,
    onValueChange: (String) -> Unit = {},
) {

    Column(modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)) {
        val textFieldValue = remember {
            mutableStateOf(note.text)
        }
        Text(text = note.title, fontWeight = FontWeight.Bold, fontSize = 24.sp)
        BasicTextField(
            modifier = modifier
                .clickable {

                }
                .fillMaxHeight(),
            value = textFieldValue.value, onValueChange = {
                textFieldValue.value = it
            }
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun NoteScreenPreview() {
//    NoteScreen(note = NoteItemUiModel.getDefault())
//}