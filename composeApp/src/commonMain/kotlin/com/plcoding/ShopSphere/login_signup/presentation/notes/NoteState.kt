package com.plcoding.ShopSphere.login_signup.presentation.notes

data class NoteState(
    val title: String = "",
    val body: String = "",
    val noteList: List<Notes> = emptyList() ,
    val isLoading: Boolean = false,
    val error: String? = null,
    val success: Boolean= false
)

