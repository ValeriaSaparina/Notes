package com.example.notes.impl.presentation.model

import com.example.common.SectionItem
import com.example.notes.api.model.FolderModel

data class NoteItemUiModel(
    val id: Long,
    val title: String,
    val text: String,
    val createDate: String,
    val editDate: String,
    val folder: FolderModel,
) : SectionItem {
    companion object {
        fun getDefault() =
            NoteItemUiModel(
                id = -1,
                title = "Title",
                text = "Emphasis, aka italics, with *asterisks* or _underscores_. Strong emphasis, aka bold, with **asterisks** or __underscores__. Combined emphasis with **asterisks and _underscores_**. Strikethrough uses two tildes. ~~Scratch this.~~ **This is bold text** __This is bold text__ *This is italic text* _This is italic text_ ~~Strikethrough~~",
                createDate = "05.12.2024",
                editDate = "05.12.2024",
                folder = FolderModel(-1, "Test")
            )

        fun getDefaultList() =
            listOf(
                getDefault().copy(id = 0),
                getDefault().copy(id = 19),
                getDefault().copy(id = 12),
                getDefault().copy(id = 13),
                getDefault().copy(id = 14),
                getDefault().copy(id = 15),
                getDefault().copy(id = 17),
                getDefault().copy(id = 16),
                getDefault().copy(id = 18),
                getDefault().copy(id = 9),
                getDefault().copy(id = 10),
            )
    }
}
