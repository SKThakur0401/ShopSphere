package com.plcoding.ShopSphere.login_signup.presentation.notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.plcoding.ShopSphere.core.data.Constants.MY_NOTES
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.postgrest.from
import io.github.jan.supabase.realtime.PostgresAction
import io.github.jan.supabase.realtime.RealtimeChannel
import io.github.jan.supabase.realtime.channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import io.github.jan.supabase.realtime.channel
import io.github.jan.supabase.realtime.postgresChangeFlow
import io.github.jan.supabase.realtime.realtime
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.jsonPrimitive
import kotlinx.serialization.json.long

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

    private fun showError(error: String){
        _state.update {
            it.copy(error = error, isLoading = false)
        }
    }

    private fun fetchNotes(){
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
//        startPolling()        // fetching data every 3 sec, but realtime channel is better
        fetchNotes()
        observeNotes()
    }


    private var realtimeChannel: RealtimeChannel? = null

    private fun observeNotes() {
        viewModelScope.launch {
            try {
                // Create channel first
                realtimeChannel = supabase.channel("notes-channel")

                // Set up the postgres change flow BEFORE subscribing
                val changeFlow = realtimeChannel?.postgresChangeFlow<PostgresAction>(
                    schema = "public"
                ) {
                    table = MY_NOTES  // Your table name
                }

                // Subscribe to the channel first
                realtimeChannel?.subscribe(blockUntilSubscribed = true)

                // Then collect the flow
                changeFlow?.onEach { action ->
                    when (action) {
                        is PostgresAction.Insert -> {
                            handleInsert(action.record)
                        }
                        is PostgresAction.Update -> {
                            handleUpdate(action.record, action.oldRecord)
                        }
                        is PostgresAction.Delete -> {
                            handleDelete(action.oldRecord)
                        }
                        else -> {
                            // Handle other actions if needed
                        }
                    }
                }?.launchIn(viewModelScope)

            } catch (ex: Exception) {
                showError("NotesVM Subscription Error: ${ex.message}")
            }
        }
    }


    private fun handleInsert(record: JsonObject) {
        try {
            val json = Json { ignoreUnknownKeys = true }
            val newNote = json.decodeFromJsonElement<Notes>(record)

            _state.update { old ->
                old.copy(noteList = old.noteList + newNote)
            }
        } catch (e: Exception) {
            showError("Error handling insert: ${e.message}")
        }
    }

    private fun handleUpdate(newRecord: JsonObject, oldRecord: JsonObject?) {
        try {
            val json = Json { ignoreUnknownKeys = true }
            val updatedNote = json.decodeFromJsonElement<Notes>(newRecord)

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

    private fun handleDelete(oldRecord: JsonObject?) {
        try {
            oldRecord?.let { record ->
                val id = record["id"]?.jsonPrimitive?.long ?: return
                _state.update { old ->
                    val filteredList = old.noteList.filter { it.id != id }
                    old.copy(noteList = filteredList)
                }
            }
        } catch (e: Exception) {
            showError("Error handling delete: ${e.message}")
        }
    }
}

