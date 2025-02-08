package com.example.feature.folders.api.repository

import com.example.common.SectionItem
import com.example.feature.folders.api.model.FoldersListModel

interface FoldersRepository {
    suspend fun getAllFolders(): List<FoldersListModel>
    suspend fun getAllLocalFolders(): List<SectionItem>
    suspend fun getAllRemoteFolders(): List<SectionItem>
    suspend fun createFolder(folderName: String, isSync: Boolean): String
}