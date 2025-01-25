package com.example.feature.folders.api.model

import androidx.annotation.StringRes


data class FoldersListModel(
    @StringRes val title: Int,
    val folders: List<FolderModel>
)