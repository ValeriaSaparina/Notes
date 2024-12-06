package com.example.notes.ui.base.utils

import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.snapshots.SnapshotStateMap
import androidx.compose.runtime.toMutableStateMap
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import com.example.notes.ui.base.component.folder.FolderItem
import com.example.notes.ui.base.component.notes.NoteItem
import com.example.notes.ui.base.data.FolderItemUiModel
import com.example.notes.ui.base.data.NoteItemUiModel
import com.example.notes.ui.base.data.SectionItem

@Composable
fun GetImage(
    @DrawableRes id: Int,
    modifier: Modifier = Modifier,
    textDescription: String = "",
    contentScale: ContentScale = ContentScale.Fit
) {
    Image(
        modifier = modifier,
        painter = painterResource(id = id),
        contentDescription = textDescription,
        contentScale = contentScale
    )
}

fun <K, V> snapshotStateMapSaver() = Saver<SnapshotStateMap<K, V>, Any>(
    save = { state -> state.toList() },
    restore = { value ->
        @Suppress("UNCHECKED_CAST")
        (value as? List<Pair<K, V>>)?.toMutableStateMap() ?: mutableStateMapOf()
    }
)

@Composable
fun <K, V> rememberSavableSnapshotStateMap(init: () -> SnapshotStateMap<K, V>): SnapshotStateMap<K, V> =
    rememberSaveable(saver = snapshotStateMapSaver(), init = init)

@Composable
fun ExpandedListItem(modifier: Modifier = Modifier, data: SectionItem, isDivider: Boolean = false) {
    when (data) {
        is FolderItemUiModel -> {
            FolderItem(
                modifier = modifier,
                data = data,
                isDivider = isDivider
            )
        }

        is NoteItemUiModel -> {
            NoteItem(
                modifier = modifier,
                data = data,
                isDivider = isDivider
            )
        }
    }
}