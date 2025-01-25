package com.example.notes.impl.mapper

import com.example.db.room.entity.NoteEntity
import com.example.db.room.entity.NoteWithFolderEntity
import com.example.notes.api.model.NoteDomainModel
import com.example.notes.api.model.FolderModel
import com.example.notes.impl.presentation.model.NoteItemUiModel
import com.example.utils.resource.TimeUtil

//fun NoteEntity.toDomain(): NoteDomainModel =
//    NoteDomainModel(
//        id = id,
//        title = title,
//        text = text,
//        createDate = createDate,
//        editDate = editDate,
//        folderName = folderName
//    )

fun NoteWithFolderEntity.toDomain(): NoteDomainModel {
    return NoteDomainModel(
        id = note.id,
        title = note.title,
        text = note.text,
        createDate = note.createDate,
        editDate = note.editDate,
        folder = FolderModel(
            id = folder.id,
            name = folder.name
        )
    )
}

fun List<NoteWithFolderEntity>.toDomain(): List<NoteDomainModel> = map { it.toDomain() }

//fun List<NoteEntity>.toDomain(): List<NoteDomainModel> =
//    map { it.toDomain() }

fun NoteDomainModel.toData(): NoteEntity =
    NoteEntity(
        id = id,
        title = title,
        text = text,
        createDate = createDate,
        editDate = editDate,
        folderId = folder.id
    )

fun NoteDomainModel.toUi(): NoteItemUiModel =
    NoteItemUiModel(
        id = id,
        title = title,
        text = text,
        createDate = TimeUtil.formatTimeWithDefaultPattern(createDate),
        editDate = TimeUtil.formatTimeWithDefaultPattern(editDate),
        folder = FolderModel(
            id = folder.id,
            name = folder.name,
        )
    )

fun List<NoteDomainModel>.toUi(): List<NoteItemUiModel> = map { it.toUi() }

fun NoteItemUiModel.toDomain(): NoteDomainModel {
    return NoteDomainModel(
        id = id,
        title = title,
        text = text,
        createDate = TimeUtil.parseTimeToUTCSystemDefaults(createDate),
        editDate = TimeUtil.parseTimeToUTCSystemDefaults(editDate),
        folder = FolderModel(id = folder.id, name = folder.name)
    )
}