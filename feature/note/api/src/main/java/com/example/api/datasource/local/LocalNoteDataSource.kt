package com.example.api.datasource.local

import com.example.db.room.entity.NoteEntity
import com.example.db.room.entity.NoteWithFolderEntity

interface LocalNoteDataSource {
    suspend fun saveNote(note: NoteEntity)

    suspend fun getNoteById(noteId: Long): NoteWithFolderEntity
}