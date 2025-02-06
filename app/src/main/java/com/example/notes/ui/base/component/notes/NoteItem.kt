package com.example.notes.ui.base.component.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.ui.base.data.NoteItemUiModel
import com.example.notes.ui.base.utils.GetImage

@Composable
fun NoteItem(modifier: Modifier = Modifier, data: NoteItemUiModel, isDivider: Boolean = false) {
    Column {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .background(Color.White)
                .padding(vertical = 4.dp, horizontal = 16.dp),
        ) {
            Text(
                text = data.title,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
//                fontSize = com.example.designsystem.Typography.bodyLarge.fontSize
            )
            Row {
                Text(text = data.dateOfEditing/* fontSize = bodySmall.fontSize*/)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = data.text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
//                    fontSize = com.example.designsystem.Typography.bodySmall.fontSize
                )
            }
            Row(
                modifier = Modifier.height(IntrinsicSize.Max),
                verticalAlignment = Alignment.CenterVertically
            ) {
                GetImage(id = R.drawable.outline_folder_24)
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = data.nameFolder,
//                    fontSize = com.example.designsystem.Typography.bodySmall.fontSize,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
        }
        if (isDivider) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(1.dp)
                    .background(color = Color.Black)
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun NoteItemPreview() {
//    NoteItem(data = NoteItemUiModel.getDefault())
//}
//
//@Preview(showBackground = true)
//@Composable
//fun NoteItemListPreview() {
//    LazyColumn {
//        val list = listOf(
//            NoteItemUiModel.getDefault(),
//            NoteItemUiModel.getDefault(),
//            NoteItemUiModel.getDefault(),
//            NoteItemUiModel.getDefault()
//        )
//        itemsIndexed(
//            list
//        ) { index, data ->
//            NoteItem(data = data, isDivider = list.lastIndex != index)
//        }
//    }
//}