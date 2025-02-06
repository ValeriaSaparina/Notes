package com.example.notes.impl.presentation.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.example.designsystem.R
import com.example.notes.api.model.NoteItemUiModel

@Composable
fun NoteItem(
    modifier: Modifier = Modifier,
    note: NoteItemUiModel,
    isDivider: Boolean = false,
    onItemClick: (Long, Long) -> Unit,
) {
    Column {
        Column(
            modifier = modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Max)
                .background(Color.White)
                .padding(vertical = 12.dp, horizontal = 16.dp)
                .clickable {
                    onItemClick(note.id, note.folder.id)
                },
        ) {
            Text(
                text = note.title,
                maxLines = 1,
                fontWeight = FontWeight.Bold,
                fontSize = com.example.designsystem.Typography.bodyLarge.fontSize
            )
            Row {
                Text(
                    text = note.createDate,
                    fontSize = com.example.designsystem.Typography.bodySmall.fontSize
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = note.text,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = com.example.designsystem.Typography.bodySmall.fontSize
                )
            }
            Row(
                modifier = Modifier.height(IntrinsicSize.Max),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.outline_folder_24),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = note.folder.name,
                    fontSize = com.example.designsystem.Typography.bodySmall.fontSize,
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
//    NoteItem(note = NoteItemUiModel.getDefault(), onItemClick = ::func)
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
//            NoteItem(note = data, isDivider = list.lastIndex != index, onItemClick = ::func)
//        }
//    }
//}

private fun func(id1: Long, id2: Long) {}