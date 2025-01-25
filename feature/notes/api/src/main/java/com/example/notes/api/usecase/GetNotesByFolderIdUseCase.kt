package com.example.notes.api.usecase

import com.example.notes.api.model.NoteDomainModel

interface GetNotesByFolderIdUseCase {

    suspend operator fun invoke(folderId: Long): Result<List<NoteDomainModel>>

}