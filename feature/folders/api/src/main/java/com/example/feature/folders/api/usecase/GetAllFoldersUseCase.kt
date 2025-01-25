package com.example.feature.folders.api.usecase

import com.example.feature.folders.api.model.FoldersListModel

interface GetAllFoldersUseCase {
    suspend operator fun invoke(): Result<List<FoldersListModel>>
}