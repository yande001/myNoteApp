package com.example.darren.mynoteapp.screen

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.darren.mynoteapp.R
import com.example.darren.mynoteapp.components.NoteButton
import com.example.darren.mynoteapp.components.NoteInputText
import com.example.darren.mynoteapp.model.Note
import com.example.darren.mynoteapp.util.formatDate


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun NoteScreen(
    notes: List<Note>,
    onAddNote: (Note) -> Unit,
    onRemoveNote: (Note) -> Unit
){
    val titleState = remember {
        mutableStateOf("")
    }
    val descriptionState = remember{
        mutableStateOf("")
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier,
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TopAppBar(
            title = {
                Text(text = stringResource(id = R.string.app_name))
            },
            actions = {
                Icon(
                    imageVector = Icons.Rounded.Notifications,
                    contentDescription = ""
                )
            },
            backgroundColor = colorResource(id = R.color.purple_200)
        )
        NoteInputText(
            text = titleState.value,
            label = "Title",
            onTextChanged = {
                if(it.all {
                    char ->
                    char.isLetter() || char.isWhitespace()
                }) titleState.value = it
            })
        NoteInputText(
            text = descriptionState.value,
            label = "Add a note",
            onTextChanged = {
                if(it.all {
                            char ->
                        char.isLetter() || char.isWhitespace()
                    }) descriptionState.value = it
            })
        NoteButton(
            modifier = Modifier
                .padding(vertical =  10.dp),
            text = "Save",
            onClick = {
                if (titleState.value.isNotEmpty() || descriptionState.value.isNotEmpty()){
                    onAddNote(Note(title = titleState.value, description = descriptionState.value))
                    titleState.value = ""
                    descriptionState.value = ""
                    Toast.makeText(context,"Note Added", Toast.LENGTH_SHORT).show()
                }
            })
        LazyColumn{
            items(notes){
                    note ->
                    NoteRow(note = note, onNoteClicked = {
                        onRemoveNote(note)

                    })

            }
        }
    }
}

@Composable
fun NoteRow(
    modifier: Modifier = Modifier,
    note: Note,
    onNoteClicked: (Note) -> Unit
){
    Surface(
        modifier = modifier
            .padding(4.dp)
            .fillMaxWidth()
            .clip(RoundedCornerShape(topEnd = 12.dp, bottomStart = 12.dp)),
        color = colorResource(id = R.color.teal_200),
        elevation = 4.dp
    ) {
        Column(
            modifier = modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .clickable {
                           onNoteClicked(note)
                },
            horizontalAlignment = Alignment.Start
        ) {
            Text(
                text = note.title,
                style = MaterialTheme.typography.subtitle2
            )
            Text(text = note.description,
                style = MaterialTheme.typography.subtitle1
            )
            Text(
                text = formatDate(note.entryDate.time) ,
                style = MaterialTheme.typography.caption

            )
        }
    }

}

