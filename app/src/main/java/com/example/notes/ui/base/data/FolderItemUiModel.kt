package com.example.notes.ui.base.data

import androidx.annotation.DrawableRes
import com.example.notes.R

data class FolderItemUiModel(
    val id: String,
    @DrawableRes val iconId: Int,
    val name: String,
    val notesNumber: String
) : SectionItem {
    companion object {
        fun getDefault() = FolderItemUiModel(
            id = "testId",
            iconId = R.drawable.outline_folder_24,
            name = "Test text",
            notesNumber = " -999"
        )
    }
}