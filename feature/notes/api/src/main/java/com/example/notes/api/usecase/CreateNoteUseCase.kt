package com.example.notes.api.usecase

import com.example.notes.api.model.NoteDomainModel

interface CreateNoteUseCase {

    suspend operator fun invoke(note: NoteDomainModel, isSync: Boolean): Result<String>

}