package com.example.feature.folders.api.model

import com.example.common.SectionItem

data class FolderModel(
    val id: Long,
    val name: String,
    val notes: Int = 0,
) : SectionItem {
    companion object {
        fun getDefault() = FolderModel(
            id = 0,
            name = "Test text",
            notes = -999
        )
    }
}