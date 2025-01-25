package com.example.studentapp.model.getNote

data class ShowNoteResponse(
    val notes: List<Note>,
    val success: Boolean
)