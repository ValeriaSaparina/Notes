package com.example.auth.impl.presentation.model

import com.example.designsystem.ViewEvent
import com.example.designsystem.ViewSideEffect
import com.example.designsystem.ViewState

class SignInContract {

    sealed class Event : ViewEvent {
        data class OnSignInClicked(val email: String, val password: String) :
            Event()

        data object OnSignUpClicked : Event()
        data object SendWrongCredentials : Event()
    }

    data class UiState(
        val wrongCredentials: Boolean,
        val isLoading: Boolean,
        val isError: Boolean,
    ) : ViewState

    sealed class Effect : ViewSideEffect {
        sealed class Navigation : Effect() {
            data class ToFolders(val userId: String) : Navigation()
            data object ToSignUp : Navigation()
        }
    }

}