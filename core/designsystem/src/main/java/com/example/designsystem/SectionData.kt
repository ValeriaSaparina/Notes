package com.example.designsystem

import androidx.annotation.StringRes
import com.example.common.SectionItem

data class SectionData(@StringRes val headerId: Int, val items: MutableList<SectionItem>) {
    companion object {
//        fun getDefaultList() = listOf(
//            SectionData(
//                "Folders",
//                items = mutableListOf(
//                    FolderItemUiModel.getDefault(),
//                    FolderItemUiModel.getDefault(),
//                    FolderItemUiModel.getDefault()
//                )
//            ),
//            SectionData(
//                "Tags",
//                items = mutableListOf(
//                    FolderItemUiModel.getDefault(),
//                    FolderItemUiModel.getDefault(),
//                    FolderItemUiModel.getDefault()
//                )
//            )
//        )
    }
}