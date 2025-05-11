package com.example.impl.repository

import androidx.core.text.isDigitsOnly
import com.example.api.datasource.local.LocalNoteDataSource
import com.example.api.repository.NoteRepository
import com.example.notes.api.datasource.RemoteNotesDataSource
import com.example.notes.api.mapper.toData
import com.example.notes.api.mapper.toDomain
import com.example.notes.api.mapper.toEntity
import com.example.notes.api.model.NoteDomainModel

class NoteRepositoryImpl(
    private val localDataSource: LocalNoteDataSource,
    private val remoteDataSource: RemoteNotesDataSource,
) : NoteRepository {
    override suspend fun updateNote(note: NoteDomainModel) {
        if (note.id.isDigitsOnly()) {
            localDataSource.saveNote(note.toEntity())
        } else {
            remoteDataSource.updateNote(note.toData())
        }
    }

    override suspend fun getNoteById(noteId: String): NoteDomainModel {
        return try {
            localDataSource.getNoteById(noteId.toLong()).toDomain()
        } catch (e: Exception) {
            remoteDataSource.getNoteById(noteId).toDomain()
        }
    }
}