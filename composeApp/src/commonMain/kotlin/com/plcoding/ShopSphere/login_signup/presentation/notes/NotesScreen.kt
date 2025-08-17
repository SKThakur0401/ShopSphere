package com.plcoding.ShopSphere.login_signup.presentation.notes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.plcoding.ShopSphere.app.accentColor
import com.plcoding.ShopSphere.app.darkText
import com.plcoding.ShopSphere.app.primaryColor
import com.plcoding.ShopSphere.app.secondaryColor
import com.plcoding.ShopSphere.core.presentation.GlobalLoader
import com.plcoding.ShopSphere.core.presentation.GlobalToast
import kotlinx.serialization.Serializable


@Composable
fun NotesScreenRoot(viewModel: NotesViewModel) {
    val state by viewModel.state.collectAsStateWithLifecycle()

//    LaunchedEffect(Unit){
//        viewModel.fetchNotes()
//    }

    NotesScreen(state, {text->
        viewModel.onAction(NotesAction.OnTextChange(text))
    }, viewModel)
}


@Composable
fun NotesScreen(state: NoteState, onTextChanged: (text: String) -> Unit, viewModel: NotesViewModel){

    state.error?.let {
        LaunchedEffect(it){
            GlobalToast.state.show(it)
        }
    }

    LaunchedEffect(state.isLoading){
        GlobalLoader.isLoading = state.isLoading
    }

    Box(modifier = Modifier.fillMaxSize()) {
        // show loader only while loading
        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.Center)
            )
        }

        // Your main content goes here
        // Example:
        // TextField(value = ..., onValueChange = onTextChanged)
    }


    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(state.noteList){note->
                NoteItem(note)
            }
        }

        Row(modifier = Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = state.title,
                onValueChange = {onTextChanged(it)},
                modifier = Modifier
                    .weight(5f)
                    .padding(bottom = 16.dp),
                shape = MaterialTheme.shapes.medium,
                placeholder = { Text("Type Note here...", color = darkText.copy(alpha = 0.7f)) },
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    textColor = darkText,
                    focusedBorderColor = primaryColor,
                    unfocusedBorderColor = secondaryColor.copy(alpha = 0.5f),
                    cursorColor = accentColor
                )
            )

            IconButton(
                onClick = { viewModel.onAction(NotesAction.OnSubmitClick) },
                modifier = Modifier.weight(1f)
            ){
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = accentColor)
            }
        }
    }
}

@Serializable
data class Notes(
    val id : Long? = null,
    val title: String = "",
    val body : String = ""
)


@Composable
fun NoteItem(note : Notes){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .background(secondaryColor.copy(alpha = 0.4f))
            .fillMaxWidth()
    ) {
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(12.dp),
            modifier = Modifier.fillMaxWidth().background(accentColor)
        ){
            Text(note.title, color = darkText)
            Text(note.body, color = darkText.copy(alpha= 0.9f))
        }
    }
}