package com.example.feature.folders.api.datasources

import com.example.db.room.model.FolderDataModel

interface RemoteFoldersDataSource {
    suspend fun getAllRemoteFolders(): List<FolderDataModel>
    suspend fun getRemoteFolderById(id: String): FolderDataModel
    suspend fun createFolder(folderName: String): String
}