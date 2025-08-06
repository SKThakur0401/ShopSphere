package com.plcoding.ShopSphere.login_signup.presentation.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.plcoding.ShopSphere.app.accentColor
import com.plcoding.ShopSphere.app.darkText
import com.plcoding.ShopSphere.app.primaryColor
import com.plcoding.ShopSphere.app.secondaryColor
import kotlinx.coroutines.launch



@Composable
fun NotesScreen(){

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        var noteList by remember {mutableStateOf<List<Notes>>(lsd)}

        LazyColumn(
            modifier = Modifier.fillMaxWidth().weight(1f),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(noteList){note->
                NoteItem(note)
            }
        }

        var tf by remember { mutableStateOf("") }

        val scope = rememberCoroutineScope()
        Row(modifier = Modifier.padding(20.dp)) {
            OutlinedTextField(
                value = tf,
                onValueChange = {tf = it},
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
                onClick = {
                    val nn = ArrayList(noteList)
                    nn.add(Notes(69, tf, "ifjei"))
                    noteList = nn }
                ,
                modifier = Modifier.weight(1f)
            ){
                Icon(imageVector = Icons.Default.Star, contentDescription = null, tint = accentColor)
            }
        }
    }
}

data class Notes(
    val id : Int,
    val title: String,
    val body : String
)

val lsd = arrayListOf(
    Notes(1, "ifej", "My description"),
    Notes(2, "ifej", "My description"),
    Notes(3, "ifej", "My description"),
    Notes(4, "ifej", "My description"),
    Notes(5, "ifej", "My description"),
)

@Composable
fun NoteItem(note : Notes){
    Card(
        modifier = Modifier
            .padding(10.dp)
            .background(secondaryColor.copy(alpha = 0.4f))
            .fillMaxWidth()
    ) {
        Row (
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth()
        ){
            Text(note.title, color = darkText)
            Text(note.body, color = darkText)
        }
    }
}