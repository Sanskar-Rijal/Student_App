package com.example.studentapp.model.Notices

data class NoticeResponse(
    val notices: List<Notice>,
    val success: Boolean
)