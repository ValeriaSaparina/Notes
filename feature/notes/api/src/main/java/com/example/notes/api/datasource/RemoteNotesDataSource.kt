package com.example.notes.api.datasource

import com.example.db.room.model.NoteDataModel
import com.example.db.room.model.NoteWithFolderDataModel

interface RemoteNotesDataSource {
    suspend fun getNotesByFolderId(folderId: String): List<NoteWithFolderDataModel>
    suspend fun getNoteById(noteId: String): NoteWithFolderDataModel
    suspend fun createNote(note: NoteDataModel): String

}