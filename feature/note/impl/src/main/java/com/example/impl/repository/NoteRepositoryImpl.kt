package com.example.impl.repository

import com.example.api.datasource.local.LocalNoteDataSource
import com.example.api.repository.NoteRepository
import com.example.notes.api.datasource.RemoteNotesDataSource
import com.example.notes.api.mapper.toDomain
import com.example.notes.api.mapper.toEntity
import com.example.notes.api.model.NoteDomainModel

class NoteRepositoryImpl(
    private val localDataSource: LocalNoteDataSource,
    private val remoteDataSource: RemoteNotesDataSource,
) : NoteRepository {
    override suspend fun updateNote(note: NoteDomainModel) {
        localDataSource.saveNote(note.toEntity())
    }

    override suspend fun getNoteById(noteId: String): NoteDomainModel {
        return try {
            localDataSource.getNoteById(noteId.toLong()).toDomain()
        } catch (e: Exception) {
            remoteDataSource.getNoteById(noteId).toDomain()
        }
    }
}