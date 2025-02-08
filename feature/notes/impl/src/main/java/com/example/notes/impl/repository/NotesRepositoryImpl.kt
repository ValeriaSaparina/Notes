package com.example.notes.impl.repository

import android.util.Log
import com.example.notes.api.datasource.LocalNotesDataSource
import com.example.notes.api.datasource.RemoteNotesDataSource
import com.example.notes.api.mapper.toData
import com.example.notes.api.mapper.toDomain
import com.example.notes.api.mapper.toDomainFromEntity
import com.example.notes.api.mapper.toEntity
import com.example.notes.api.model.NoteDomainModel
import com.example.notes.api.repository.NotesRepository

class NotesRepositoryImpl(
    private val localDataSource: LocalNotesDataSource,
    private val remoteDataSource: RemoteNotesDataSource,
) : NotesRepository {
    override suspend fun getNotesByFolderId(folderId: String): List<NoteDomainModel> {
        return try {
            localDataSource.getNotesByFolderId(folderId.toLong()).toDomainFromEntity()
        } catch (e: NumberFormatException) {
            remoteDataSource.getNotesByFolderId(folderId).toDomain()
        }
    }

    override suspend fun getNoteById(noteId: Long): NoteDomainModel {
        return localDataSource.getNoteById(noteId).toDomain()
    }

    override suspend fun createNote(note: NoteDomainModel, isSync: Boolean): String {
        return try {
            note.folder.id.toLong()
            Log.d("CREATING NOTE", "Local")
            localDataSource.createNote(note.toEntity()).toString()
        } catch (e: Exception) {
            Log.d("CREATING NOTE", "Remote")
            remoteDataSource.createNote(note.toData())
        }
    }
}