package com.example.feature.folders.api.usecase

import com.example.feature.folders.api.model.FolderModel

interface CreateFolderUseCase {
    suspend operator fun invoke(folderName: String): Result<Long>
}