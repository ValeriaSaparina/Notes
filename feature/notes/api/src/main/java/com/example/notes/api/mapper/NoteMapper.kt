package com.example.notes.api.mapper

import android.util.Log
import com.example.db.room.entity.NoteEntity
import com.example.db.room.entity.NoteWithFolderEntity
import com.example.db.room.model.NoteDataModel
import com.example.db.room.model.NoteWithFolderDataModel
import com.example.notes.api.model.FolderModel
import com.example.notes.api.model.NoteDomainModel
import com.example.notes.api.model.NoteItemUiModel
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
    Log.d("GET NOTE", "WITH FOLDER: ${this}")
    return NoteDomainModel(
        id = note.id.toString(),
        title = note.title,
        text = note.text,
        createDate = note.createDate,
        editDate = note.editDate,
        folder = FolderModel(
            id = folder.id.toString(),
            name = folder.name
        )
    )
}

fun NoteWithFolderDataModel.toDomain(): NoteDomainModel {
    Log.d("GET NOTE", "WITH FOLDER: ${this}")
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

fun List<NoteWithFolderEntity>.toDomainFromEntity(): List<NoteDomainModel> = map { it.toDomain() }
fun List<NoteWithFolderDataModel>.toDomain(): List<NoteDomainModel> = map { it.toDomain() }

//fun List<NoteEntity>.toDomain(): List<NoteDomainModel> =
//    map { it.toDomain() }

fun NoteDomainModel.toEntity(): NoteEntity =
    NoteEntity(
        id = id.toLong(),
        title = title,
        text = text,
        createDate = createDate,
        editDate = editDate,
        folderId = folder.id.toLong()
    )

fun NoteDomainModel.toData(): NoteDataModel =
    NoteDataModel(
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