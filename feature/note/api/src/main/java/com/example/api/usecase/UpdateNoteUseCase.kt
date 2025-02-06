package com.example.api.usecase

import com.example.notes.api.model.NoteDomainModel

interface UpdateNoteUseCase {
    suspend operator fun invoke(note: NoteDomainModel): Result<Unit>
}
