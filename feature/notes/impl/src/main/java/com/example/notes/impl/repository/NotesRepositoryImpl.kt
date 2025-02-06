package com.example.notes.impl.repository

import com.example.notes.api.datasource.LocalNotesDataSource
import com.example.notes.api.model.NoteDomainModel
import com.example.notes.api.repository.NotesRepository
import com.example.notes.api.mapper.toData
import com.example.notes.api.mapper.toDomain

class NotesRepositoryImpl(
    private val localDataSource: LocalNotesDataSource,
) : NotesRepository {
    override suspend fun getNotesByFolderId(folderId: Long): List<NoteDomainModel> {
        return localDataSource.getNotesByFolderId(folderId).toDomain()
    }

    override suspend fun getNoteById(noteId: Long): NoteDomainModel {
        return localDataSource.getNoteById(noteId).toDomain()
    }

    override suspend fun createNote(note: NoteDomainModel, isSync: Boolean): Long {
        val noteId = localDataSource.createNote(note.toData())
        if (isSync) {
            // TODO: remote sync
            note.copy(id = noteId)
        }
        return noteId
    }
}