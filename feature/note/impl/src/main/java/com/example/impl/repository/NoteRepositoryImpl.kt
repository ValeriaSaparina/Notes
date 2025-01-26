package com.example.impl.repository

import android.util.Log
import com.example.api.datasource.local.LocalNoteDataSource
import com.example.api.repository.NoteRepository
import com.example.notes.api.mapper.*
import com.example.notes.api.model.NoteDomainModel

class NoteRepositoryImpl(
    private val localDataSource: LocalNoteDataSource
) : NoteRepository {
    override suspend fun updateNote(note: NoteDomainModel) {
        localDataSource.saveNote(note.toData())
    }

    override suspend fun getNoteById(noteId: Long): NoteDomainModel {
        val note = localDataSource.getNoteById(noteId)
        Log.d("GET NOTE", note.note.text)
        return localDataSource.getNoteById(noteId).toDomain()
    }
}