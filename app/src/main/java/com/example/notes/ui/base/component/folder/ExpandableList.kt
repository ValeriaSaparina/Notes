package com.example.notes.ui.base.component.folder

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.ui.base.data.FolderItemUiModel
import com.example.notes.ui.base.data.SectionData
import com.example.notes.ui.base.utils.ExpandedListItem
import com.example.notes.ui.base.utils.rememberSavableSnapshotStateMap

@Composable
fun ExpandableList(sectionDataData: List<SectionData>) {
    val isExpandedMap = rememberSavableSnapshotStateMap {
        List(sectionDataData.size) { index: Int -> index to true }.toMutableStateMap()
    }

    LazyColumn(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        sectionDataData.onEachIndexed { index, sectionData ->
            this.section(sectionData = sectionData, isExpanded = isExpandedMap[index] ?: true) {
                isExpandedMap[index] = !(isExpandedMap[index] ?: true)
            }
        }
    }
}


fun LazyListScope.section(
    sectionData: SectionData,
    isExpanded: Boolean,
    onHeaderClick: () -> Unit
) {
    item {
        SectionHeader(
            sectionData = sectionData,
            isExpanded = isExpanded,
            onHeaderClick = onHeaderClick
        )
    }
    itemsIndexed(sectionData.items) { index, item ->
        val modifier = when (index) {
            0 -> {
                Modifier.clip(RoundedCornerShape(24.dp, 24.dp, 0.dp, 0.dp))
            }

            sectionData.items.lastIndex -> {
                Modifier.clip(RoundedCornerShape(0.dp, 0.dp, 24.dp, 24.dp))
            }

            else -> {
                Modifier
            }
        }
        AnimatedVisibility(
            visible = isExpanded,
            enter = fadeIn(animationSpec = tween(ANIMATION_DURATION)) + expandVertically(
                animationSpec = tween(ANIMATION_DURATION)
            ),
            exit = fadeOut(animationSpec = tween(ANIMATION_DURATION)) + shrinkVertically(
                animationSpec = tween(ANIMATION_DURATION)
            )
        ) {
            ExpandedListItem(
                modifier = modifier,
                data = item,
                isDivider = index != sectionData.items.lastIndex
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ExpandableListPreview() {
    ExpandableList(
        listOf(
            SectionData(
                "Remote",
                items = listOf(
                    FolderItemUiModel.getDefault(),
                    FolderItemUiModel.getDefault(),
                    FolderItemUiModel.getDefault()
                )
            ),
            SectionData(
                "Tags",
                items = listOf(
                    FolderItemUiModel.getDefault(),
                    FolderItemUiModel.getDefault(),
                    FolderItemUiModel.getDefault()
                )
            )
        )
    )
}

private const val ANIMATION_DURATION = 500