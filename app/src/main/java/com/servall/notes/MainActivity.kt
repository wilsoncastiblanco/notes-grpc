package com.servall.notes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.material3.Card
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.servall.notes.entities.Note
import com.servall.notes.ui.BottomSheetAddNote
import com.servall.notes.ui.NotesUiState
import com.servall.notes.ui.NotesViewModel
import com.servall.notes.ui.theme.NotesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NotesTheme {

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val viewModel: NotesViewModel = viewModel()
                    val notesUiState by viewModel.notesState.collectAsStateWithLifecycle()

                    Notes(notesUiState)
                    BottomSheetAddNote { title, note ->
                        viewModel.saveNote(title, note)
                    }
                }
            }
        }
    }
}

@Composable
fun Notes(notesUiState: NotesUiState) {
    when (notesUiState) {
        is NotesUiState.Data -> Notes(Modifier, notesUiState.notes)
        is NotesUiState.Error -> ErrorMessage(notesUiState.message)
        is NotesUiState.Loading -> ProgressIndicator()
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Notes(modifier: Modifier, notes: List<Note>) {
    LazyVerticalStaggeredGrid(
        columns = StaggeredGridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(notes, key = { it.id }) { note ->
            Card {
                Column(
                    modifier = Modifier.padding(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Text(note.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
                    Text(note.note)
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun NotePreview() {
    NotesTheme {
        Notes(
            Modifier,
            listOf(
                Note(
                    id = "1",
                    date = "2023-01-23",
                    note = "This is an example of note added and propagated",
                    title = "Hello Note1!"
                ),
                Note(
                    id = "2",
                    date = "2023-01-23",
                    note = "This is an example",
                    title = "Hello Note2!"
                ),
                Note(
                    id = "3",
                    date = "2023-01-23",
                    note = "This is an example of note added and propagated his is an example of note added and propagated",
                    title = "Hello Note3!"
                ),
                Note(
                    id = "4",
                    date = "2023-01-23",
                    note = "This is",
                    title = "Hello Note3!"
                ),
                Note(
                    id = "5",
                    date = "2023-01-23",
                    note = "This is an example of note added and propagated his is an example of note added and propagated",
                    title = "Hello Note3!"
                ),
                Note(
                    id = "6",
                    date = "2023-01-23",
                    note = "This is an example of note added and propagated",
                    title = "Hello Note1!"
                ),
            )
        )
    }
}

@Composable
fun ErrorMessage(message: String) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Error! $message")
    }
}

@Composable
fun ProgressIndicator() {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        CircularProgressIndicator()
    }
}