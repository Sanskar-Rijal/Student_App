package com.example.studentapp.model.Notices

data class Notice(
    val description: String,
    val id: String,
    val section: String,
    val semester: String,
    val subject: String,
    val teacher: String,
    val title: String
)