package com.servall.notes.ui

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.TextField
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.rememberModalBottomSheetState
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun BottomSheetAddNote(onCloseSheet: (String, String) -> Unit) {

    var titleState by remember { mutableStateOf(TextFieldValue("")) }
    var noteState by remember { mutableStateOf(TextFieldValue("")) }

    val sheetState = rememberModalBottomSheetState(
        initialValue = ModalBottomSheetValue.Hidden,
        confirmStateChange = { it != ModalBottomSheetValue.HalfExpanded }
    )
    val coroutineScope = rememberCoroutineScope()

    BackHandler(sheetState.isVisible) {
        coroutineScope.launch { sheetState.hide() }
        onCloseSheet(
            titleState.text,
            noteState.text
        )
        titleState = TextFieldValue("")
        noteState = TextFieldValue("")
    }

    ModalBottomSheetLayout(
        sheetState = sheetState,
        sheetContent = {
            BottomSheet(
                titleState,
                noteState,
                onNoteChange = { noteState = it },
                onTitleChange = { titleState = it }
            )
        },
        modifier = Modifier.fillMaxSize()
    ) {

        Box(modifier = Modifier.fillMaxSize()) {

            FloatingActionButton(
                modifier = Modifier
                    .padding(all = 16.dp)
                    .align(alignment = Alignment.BottomEnd),
                onClick = {
                    coroutineScope.launch {
                        if (sheetState.isVisible) sheetState.hide()
                        else sheetState.show()
                    }
                }) {
                Icon(imageVector = Icons.Filled.Add, contentDescription = "Add")
            }
        }

    }
}


@Composable
fun BottomSheet(
    titleState: TextFieldValue,
    noteState: TextFieldValue,
    onTitleChange: (TextFieldValue) -> Unit,
    onNoteChange: (TextFieldValue) -> Unit
) {
    val modifier = Modifier.fillMaxSize()
    Column(modifier = modifier) {
        TextField(
            value = titleState,
            onValueChange = { onTitleChange(it) },
            placeholder = { Text("Title") },
            singleLine = true,
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = noteState,
            onValueChange = { onNoteChange(it) },
            placeholder = { Text("Note") },
            singleLine = false,
            modifier = Modifier.fillMaxSize()
        )
    }
}