package com.example.auth.impl.presentation.model

import com.example.designsystem.ViewEvent
import com.example.designsystem.ViewSideEffect
import com.example.designsystem.ViewState

class SignUpContract {

    sealed class Event : ViewEvent {
        data class OnSignUpClicked(val email: String, val password: String, val name: String) :
            Event()

        data object OnSignInClicked : Event()
    }

    data class UiState(
        val isWrongEmail: Boolean,
        val isWrongName: Boolean,
        val isWrongPassword: Boolean,
        val isLoading: Boolean,
        val isError: Boolean
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        data class WrongCredentials(val string: String)
        sealed class Navigation : Effect() {
            data class ToFolders(val userId: String) : Navigation()
            data object ToSignIn : Navigation()
        }
    }

}