package com.servall.notes.data

import com.servall.notes.NotesServiceGrpcKt
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder

object Services {

    private val channel: ManagedChannel = ManagedChannelBuilder
        .forAddress("10.0.2.2", 50051)
        .usePlaintext()
        .build()

    fun notesService() = NotesServiceGrpcKt.NotesServiceCoroutineStub(channel)


}