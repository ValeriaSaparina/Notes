package com.example.feature.folders.impl.presentation.model

import androidx.annotation.DrawableRes
import com.example.common.SectionItem
import com.example.designsystem.R

class FolderUiModel(
    val id: Long,
    @DrawableRes
    val iconId: Int = R.drawable.outline_folder_24,
    val name: String,
    val notesNumber: Int = 0,
) : SectionItem {
    companion object {
        fun getDefault() = FolderUiModel(
            id = 0,
            iconId = R.drawable.outline_folder_24,
            name = "Test text",
            notesNumber = -999
        )
    }
}