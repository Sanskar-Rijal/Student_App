package com.example.studentapp.model.ShowAttendance

data class ShowAttendanceResponse(
    val attendanceSummary: List<AttendanceSummary>,
    val message: String,
    val success: Boolean,
    val totalClasses: Int,
    val totalPresentClasses: Int
)