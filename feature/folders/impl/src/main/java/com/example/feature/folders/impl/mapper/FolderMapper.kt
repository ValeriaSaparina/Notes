package com.example.feature.folders.impl.mapper

import com.example.db.room.entity.FolderEntity
import com.example.db.room.model.FolderDataModel
import com.example.designsystem.SectionData
import com.example.feature.folders.api.model.FolderModel
import com.example.feature.folders.api.model.FoldersListModel
import com.example.feature.folders.impl.presentation.model.FolderUiModel

fun FolderDataModel.toDomain(): FolderModel =
    FolderModel(
        id = id,
        name = name,
        notes = noteCount
    )

fun List<FolderDataModel>.toDomain(): List<FolderModel> = map { it.toDomain() }

fun FolderDataModel.toEntity(): FolderEntity =
    FolderEntity(
        id = id,
        name = name,
    )

fun List<FolderDataModel>.toEntity(): List<FolderEntity> = map { it.toEntity() }

fun FolderModel.toData(): FolderDataModel =
    FolderDataModel(
        id = id,
        name = name,
        noteCount = notes
    )

fun List<FolderModel>.toData(): List<FolderDataModel> = map { it.toData() }

fun List<FoldersListModel>.toUi(): List<SectionData> = map { it.toUi() }

private fun FoldersListModel.toUi(): SectionData {
    return SectionData(
        headerId = title, items = folders.map { it.toUi() }.toMutableList()
    )
}

fun FolderModel.toUi() =
    FolderUiModel(
        id = id,
        iconId = com.example.designsystem.R.drawable.outline_folder_24,
        name = name,
        notesNumber = notes
    )