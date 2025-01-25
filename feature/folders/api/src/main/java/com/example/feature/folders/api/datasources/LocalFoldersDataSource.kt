package com.example.feature.folders.api.datasources

import com.example.db.room.model.FolderDataModel

interface LocalFoldersDataSource {
    suspend fun getAllLocalFolders(): List<FolderDataModel>
    suspend fun getLocalFolderById(id: String): FolderDataModel
    suspend fun createFolder(folderName: String) : Long
}