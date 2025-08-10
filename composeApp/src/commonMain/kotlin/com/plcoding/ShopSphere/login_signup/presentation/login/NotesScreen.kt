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
import androidx.compose.runtime.LaunchedEffect
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
import io.github.jan.supabase.SupabaseClient
import io.github.jan.supabase.createSupabaseClient
import io.github.jan.supabase.postgrest.Postgrest
import io.github.jan.supabase.postgrest.from
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.withContext
import kotlinx.serialization.Serializable


//val supabase = createSupabaseClient(
//    supabaseUrl = "https://jntbkxrqyjefnoerkmeu.supabase.co",     // ✅ your actual project URL
//    supabaseKey = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpudGJreHJxeWplZm5vZXJrbWV1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTQ0MDc0NDksImV4cCI6MjA2OTk4MzQ0OX0.-sIlgATXTKGgGJfPWJtt512vv5-J06sq9hbHSSh9Ze4",               // ✅ your actual anon/public API key
//){
//    install(Postgrest)
//}

private const val SUPABASE_URL = "https://jntbkxrqyjefnoerkmeu.supabase.co"
private const val SUPABASE_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6ImpudGJreHJxeWplZm5vZXJrbWV1Iiwicm9sZSI6ImFub24iLCJpYXQiOjE3NTQ0MDc0NDksImV4cCI6MjA2OTk4MzQ0OX0.-sIlgATXTKGgGJfPWJtt512vv5-J06sq9hbHSSh9Ze4"

val supabase: SupabaseClient = createSupabaseClient(
    supabaseUrl = SUPABASE_URL,
    supabaseKey = SUPABASE_KEY
) {
    install(Postgrest)
}


@Composable
fun NotesScreen(){

    var noteList by remember { mutableStateOf<List<Notes>>(emptyList()) }
    var error by remember { mutableStateOf<String?>(null) }

    LaunchedEffect(Unit) {
        try {
            val result = withContext(Dispatchers.IO) {
                supabase.from("myNotes")
                    .select()
                    .decodeList<Notes>()
            }
            noteList = result
        } catch (e: Exception) {
            error = e.message
        }
    }

//    if (error != null) {
//        Text("Error: $error")
//    } else {
//        noteList.forEach { note ->
//            Text("${note.id}: ${note.title}")
//        }
//    }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
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

@Serializable
data class Notes(
    val id : Long,
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