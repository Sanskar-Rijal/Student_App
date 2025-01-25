package com.example.studentapp.model.getNote

data class Note(
    val fileUrl: String,
    val id: String,
    val section: String,
    val semester: String,
    val subjectName: String,
    val teacherName: String,
    val title: String
)