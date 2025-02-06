package com.example.notes.ui.base.component.folder

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.clickable
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.notes.R
import com.example.notes.ui.base.data.FolderItemUiModel
import com.example.notes.ui.base.data.NoteItemUiModel
import com.example.notes.ui.base.data.SectionData
import com.example.notes.ui.base.utils.ExpandedListItem
import com.example.notes.ui.base.utils.rememberSavableSnapshotStateMap

@Composable
fun ExpandableList(
    modifier: Modifier = Modifier,
    sectionData: List<SectionData>,
    onItemClicked: (String) -> Unit
) {
    val isExpandedMap = rememberSavableSnapshotStateMap {
        List(sectionData.size) { index: Int -> index to true }.toMutableStateMap()
    }

    LazyColumn(
        modifier = modifier
    ) {
        sectionData.onEachIndexed { index, sectionData ->
            this.section(
                sectionData = sectionData,
                isExpanded = isExpandedMap[index] ?: true,
                onItemClicked = onItemClicked
            ) {
                isExpandedMap[index] = !(isExpandedMap[index] ?: true)
            }
        }
    }
}


fun LazyListScope.section(
    sectionData: SectionData,
    isExpanded: Boolean,
    onItemClicked: (String) -> Unit,
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
        }.clickable {
            onItemClicked(
                when (item) {
                    is FolderItemUiModel -> item.id
                    is NoteItemUiModel -> item.id
                    else -> "-1"
                }
            )
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
        sectionData = listOf(
            SectionData(
                stringResource(id = R.string.local_name_folder),
                items = mutableListOf(
                    FolderItemUiModel.getDefault(),
                    FolderItemUiModel.getDefault(),
                    FolderItemUiModel.getDefault()
                )
            ),
            SectionData(
                "Tags",
                items = mutableListOf(
                    FolderItemUiModel.getDefault(),
                    FolderItemUiModel.getDefault(),
                    FolderItemUiModel.getDefault()
                )
            )
        )
    ) {}
}

private const val ANIMATION_DURATION = 500