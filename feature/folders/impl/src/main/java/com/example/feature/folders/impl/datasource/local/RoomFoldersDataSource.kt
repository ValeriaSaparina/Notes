package com.example.feature.folders.impl.datasource.local

import com.example.db.room.dao.FoldersDao
import com.example.db.room.entity.FolderEntity
import com.example.db.room.model.FolderDataModel
import com.example.feature.folders.api.datasources.LocalFoldersDataSource

class RoomFoldersDataSource(private val foldersDao: FoldersDao) : LocalFoldersDataSource {
    override suspend fun getAllLocalFolders(): List<FolderDataModel> {
        return foldersDao.getAllLocalFolders()
    }

    override suspend fun getLocalFolderById(id: String): FolderDataModel{
        return foldersDao.getLocalFolderById(id)
    }

    override suspend fun createFolder(folderName: String) : Long {
        return foldersDao.createLocalFolder(FolderEntity(name = folderName))
    }

}