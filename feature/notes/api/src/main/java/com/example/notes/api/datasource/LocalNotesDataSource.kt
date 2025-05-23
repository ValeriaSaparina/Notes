package com.example.notes.api.datasource

import com.example.db.room.entity.NoteEntity
import com.example.db.room.entity.NoteWithFolderEntity

interface LocalNotesDataSource {
    suspend fun getNotesByFolderId(folderId: Long): List<NoteWithFolderEntity>
    suspend fun getNoteById(noteId: Long): NoteWithFolderEntity
    suspend fun createNote(note: NoteEntity): Long

}