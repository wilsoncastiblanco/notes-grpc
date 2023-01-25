package com.servall.notes.data

import com.google.protobuf.Empty
import com.servall.notes.NoteMessage
import com.servall.notes.NotesServiceGrpcKt
import com.servall.notes.Response
import com.servall.notes.data.Services.notesService
import com.servall.notes.entities.Note
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import java.util.Date

class NotesRemoteRepository(private val service: NotesServiceGrpcKt.NotesServiceCoroutineStub = notesService()) {

    fun getNotesStream(): Flow<List<Note>> {
        return service.getNotes(Empty.getDefaultInstance())
            .map {
                it.notesList.map { remoteNote ->
                    Note(
                        id = remoteNote.id,
                        date = remoteNote.date,
                        note = remoteNote.note,
                        title = remoteNote.title
                    )
                }
            }.flowOn(Dispatchers.IO)
    }

    suspend fun saveNote(title: String, note: String): Response {
        return withContext(Dispatchers.IO) {
            val request = NoteMessage.newBuilder()
                .setDate(Date().toString())
                .setNote(note)
                .setTitle(title)
                .build()
            return@withContext service.saveNote(request)
        }
    }

}