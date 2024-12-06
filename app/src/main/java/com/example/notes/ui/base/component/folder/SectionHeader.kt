package com.example.notes.ui.base.component.folder

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.ui.base.data.FolderItemUiModel
import com.example.notes.ui.base.data.SectionData
import com.example.notes.ui.base.utils.GetImage

@Composable
fun SectionHeader(sectionData: SectionData, isExpanded: Boolean, onHeaderClick: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Max)
            .clickable(
                indication = null,
                interactionSource = remember { MutableInteractionSource() }) { onHeaderClick() }
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            modifier = Modifier
                .weight(1.0f),
            text = sectionData.headerText,
        )
        val rotate = if (isExpanded) 90F else 0F
        GetImage(id = R.drawable.outline_keyboard_arrow_right_24, modifier = Modifier.rotate(rotate))
    }

}

@Preview(showBackground = true)
@Composable
fun HeaderItemExpandedPreview() {
    SectionHeader(
        SectionData(
            "Expanded",
            items = listOf(FolderItemUiModel.getDefault(), FolderItemUiModel.getDefault())
        ), true
    ) {}
}

@Preview(showBackground = true)
@Composable
fun HeaderItemCollapsedPreview() {
    SectionHeader(
        SectionData(
            "Collapsed",
            items = listOf(FolderItemUiModel.getDefault(), FolderItemUiModel.getDefault())
        ), false
    ) {}
}