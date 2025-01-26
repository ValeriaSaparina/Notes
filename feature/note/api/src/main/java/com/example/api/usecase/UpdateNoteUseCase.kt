package com.example.api.usecase

import com.example.notes.api.model.NoteDomainModel
import com.example.notes.api.model.NoteItemUiModel

interface UpdateNoteUseCase {
    suspend operator fun invoke(note: NoteDomainModel): Result<Unit>
}
