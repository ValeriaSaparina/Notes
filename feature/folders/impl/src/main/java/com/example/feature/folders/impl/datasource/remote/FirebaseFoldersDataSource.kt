package com.example.feature.folders.impl.datasource.remote

import com.example.db.room.model.FolderDataModel
import com.example.feature.folders.api.datasources.RemoteFoldersDataSource
import com.example.feature.folders.impl.mapper.toData
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await
import java.util.UUID

class FirebaseFoldersDataSource(
    private val fireStore: FirebaseFirestore,
    private val auth: FirebaseAuth,
) : RemoteFoldersDataSource {
    override suspend fun getAllRemoteFolders(): List<FolderDataModel> {
        return fireStore.collection(FOLDERS_COLLECTION_PATH)
            .whereEqualTo("userId", auth.currentUser!!.uid).get().await().documents
            .toData()
    }

    override suspend fun getRemoteFolderById(id: String): FolderDataModel {
        return fireStore.collection(FOLDERS_COLLECTION_PATH).document(id).get()
            .await().toObject(FolderDataModel::class.java)!!
    }

    override suspend fun createFolder(folderName: String): String {
        val id = UUID.randomUUID().toString()
        val folder = hashMapOf(
            "name" to folderName,
            "id" to id,
            "noteCount" to 0,
            "userId" to auth.currentUser!!.uid
        )
        fireStore.collection(FOLDERS_COLLECTION_PATH).document(id).set(folder)
        return id
    }

    companion object {
        private const val FOLDERS_COLLECTION_PATH = "folders"
    }

}
