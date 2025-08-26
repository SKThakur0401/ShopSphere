package com.plcoding.ShopSphere.login_signup.presentation.notes

sealed interface NotesAction {
    data object OnSubmitClick: NotesAction

    data class OnTextChange(val text : String): NotesAction
}
