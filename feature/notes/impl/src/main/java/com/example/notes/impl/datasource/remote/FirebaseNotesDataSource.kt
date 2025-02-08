package com.example.notes.impl.datasource.remote

import com.example.db.room.model.FolderDataModel
import com.example.db.room.model.NoteDataModel
import com.example.db.room.model.NoteWithFolderDataModel
import com.example.notes.api.datasource.RemoteNotesDataSource
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseNotesDataSource(
    private val fireStore: FirebaseFirestore,
) : RemoteNotesDataSource {
    override suspend fun getNotesByFolderId(folderId: String): List<NoteWithFolderDataModel> {
        val folder = fireStore.collection(FOLDERS_COLLECTION_PATH).document(folderId).get().await()
            .toObject(FolderDataModel::class.java)
        val notes =
            fireStore.collection(NOTES_COLLECTION_PATH).whereEqualTo("folderId", folderId).get()
                .await().toObjects(NoteDataModel::class.java)
        return notes.map { NoteWithFolderDataModel(it, folder!!) }
    }

    override suspend fun getNoteById(noteId: String): NoteWithFolderDataModel {
        val note = fireStore.collection(NOTES_COLLECTION_PATH).document(noteId).get()
            .await().toObject(NoteDataModel::class.java)!!

        val folder = fireStore.collection(FOLDERS_COLLECTION_PATH).document(note.folderId).get()
            .await().toObject(FolderDataModel::class.java)!!
        return NoteWithFolderDataModel(
            note = note, folder = folder
        )
    }

    override suspend fun createNote(note: NoteDataModel): String {
        val id = UUID.randomUUID().toString()
        fireStore.collection(NOTES_COLLECTION_PATH).document(id).set(note.copy(id = id))
        return id
    }

    companion object {
        private const val FOLDERS_COLLECTION_PATH = "folders"
        private const val NOTES_COLLECTION_PATH = "notes"
    }
}