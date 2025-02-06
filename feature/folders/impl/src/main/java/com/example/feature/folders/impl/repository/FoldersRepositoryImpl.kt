package com.example.feature.folders.impl.repository

import com.example.designsystem.R
import com.example.feature.folders.api.datasources.LocalFoldersDataSource
import com.example.feature.folders.api.model.FolderModel
import com.example.feature.folders.api.model.FoldersListModel
import com.example.feature.folders.api.repository.FoldersRepository
import com.example.feature.folders.impl.mapper.toDomain

class FoldersRepositoryImpl(
    private val localDataSource: LocalFoldersDataSource,
) : FoldersRepository {
    override suspend fun getAllFolders(): List<FoldersListModel> {
        return listOf(
            FoldersListModel(
                title = R.string.local_name_folder,
                folders = getAllLocalFolders()
            ),
            FoldersListModel(
                title = R.string.remote_name_folder,
                folders = getAllLocalFolders()
            )
        )
    }

    override suspend fun getAllLocalFolders(): List<FolderModel> {
        return localDataSource.getAllLocalFolders().toDomain()
    }

    override suspend fun getAllRemoteFolders(): List<FolderModel> {
        TODO("Not yet implemented")
    }

    override suspend fun createFolder(folderName: String): Long {
        return localDataSource.createFolder(folderName)
    }
}