package com.example.api.usecase

import com.example.notes.api.model.NoteDomainModel

interface GetNoteByIdUseCase {
    suspend operator fun invoke(noteId: String): Result<NoteDomainModel>
}