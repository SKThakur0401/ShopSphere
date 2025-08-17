package com.plcoding.ShopSphere.login_signup.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.ShopSphere.core.data.Constants.MY_NOTES
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.delay
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive

class NotesViewModel(private val supabase : SupabaseClient): ViewModel() {

    private val _state = MutableStateFlow(NoteState())
    val state = _state.asStateFlow()

    fun onAction(action : NotesAction){
        when(action){
            is NotesAction.OnSubmitClick -> {
                addNote()
            }

            is NotesAction.OnTextChange -> {
                _state.update {
                    it.copy(title = action.text)
                }
            }
        }
    }

    private fun addNote(){
        viewModelScope.launch {
            try {
                val note = Notes(title = _state.value.title)
                _state.update {
                    it.copy(isLoading = true, title = "")
                }
                supabase.from(MY_NOTES).insert(note)

                _state.update { it.copy(isLoading = false) }
            } catch (ex: Exception){
                showError("NotesVM Error: ${ex.message}")
            }
        }
    }

    fun showError(error: String){
        _state.update {
            it.copy(error = error, isLoading = false)
        }
    }

    fun fetchNotes(){
        viewModelScope.launch {
            try {
                val notes = supabase.from(MY_NOTES).select().decodeList<Notes>()
                _state.update {
                    it.copy(noteList = notes)
                }
            } catch (ex: Exception){
                showError("NotesVM Error: ${ex.message}")
            }
        }
    }

    private fun startPolling(){
        viewModelScope.launch {
            while(true){
                fetchNotes()
                delay(3000)
            }
        }
    }

    init {
        startPolling()
    }

/*
    private var realtimeChannel: RealtimeChannel? = null


    // CURRENT WORKING APPROACH - Method 1: Using postgresListenFlow
    private fun observeNotes() {
        viewModelScope.launch {
            try {
                // Create and join a channel
                realtimeChannel = supabase.realtime.createChannel("notes-channel")

                // Listen to postgres changes using Flow
                realtimeChannel?.postgresListenFlow(
                    schema = "public",
                    table = MY_NOTES
                )?.collect { action ->
                    when (action) {
                        is PostgresAction.Insert -> {
                            handleInsert(action.record)
                        }
                        is PostgresAction.Update -> {
//                            handleUpdate(action.record)
                        }
                        is PostgresAction.Delete -> {
//                            handleDelete(action.oldRecord)
                        }
                        else -> {
                            // Handle other actions if needed
                        }
                    }
                }

                // Subscribe to the channel
                realtimeChannel?.subscribe()

            } catch (ex: Exception) {
                showError("NotesVM Subscription Error: ${ex.message}")
            }
        }
    }

    private fun handleInsert(record: JsonObject) {
        try {
            // Parse the record manually or use your preferred JSON parsing
            val id = record["id"]?.jsonPrimitive?.content ?: return
            val title = record["title"]?.jsonPrimitive?.content ?: ""
            val body = record["body"]?.jsonPrimitive?.content

            val newNote = Notes(
                id = id.toLong(),
                title = title,
                body = body ?: ""
            )

            _state.update { old ->
                old.copy(noteList = old.noteList + newNote)
            }
        } catch (e: Exception) {
            showError("Error handling insert: ${e.message}")
        }
    }
*/

/*
    private fun handleUpdate(record: JsonObject) {
        try {
            val id = record["id"]?.jsonPrimitive?.content ?: return
            val title = record["title"]?.jsonPrimitive?.content ?: ""
            val content = record["content"]?.jsonPrimitive?.content ?: ""
            val createdAt = record["created_at"]?.jsonPrimitive?.content
            val userId = record["user_id"]?.jsonPrimitive?.content

            val updatedNote = Notes(
                id = id,
                title = title,
                created_at = createdAt,
                user_id = userId
            )

            _state.update { old ->
                val updatedList = old.noteList.map { note ->
                    if (note.id == updatedNote.id) updatedNote else note
                }
                old.copy(noteList = updatedList)
            }
        } catch (e: Exception) {
            showError("Error handling update: ${e.message}")
        }
    }

    private fun handleDelete(record: JsonObject) {
        try {
            val id = record["id"]?.jsonPrimitive?.content ?: return

            _state.update { old ->
                val filteredList = old.noteList.filter { it.id != id }
                old.copy(noteList = filteredList)
            }
        } catch (e: Exception) {
            showError("Error handling delete: ${e.message}")
        }
    }
*/

}

