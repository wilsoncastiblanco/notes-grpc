package com.servall.notes.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.servall.notes.Notes
import com.servall.notes.entities.Note

@Composable
fun NotesPreview() {
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