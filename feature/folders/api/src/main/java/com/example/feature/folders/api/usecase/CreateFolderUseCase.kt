package com.example.feature.folders.api.usecase

interface CreateFolderUseCase {
    suspend operator fun invoke(folderName: String, isSync: Boolean): Result<String>
}