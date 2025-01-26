package com.example.api.usecase

import com.example.notes.api.model.NoteDomainModel
import com.example.notes.api.model.NoteItemUiModel

interface GetNoteByIdUseCase {
    suspend operator fun invoke(noteId: Long) : Result<NoteDomainModel>
}