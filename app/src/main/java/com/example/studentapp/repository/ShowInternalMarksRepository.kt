package com.example.studentapp.repository

import com.example.studentapp.data.DataorException
import com.example.studentapp.model.ShowAttendance.ShowAttendanceResponse
import com.example.studentapp.model.ShowInternalMarks.ShowInternalMarksResponse
import com.example.studentapp.network.network
import javax.inject.Inject

class ShowInternalMarksRepository @Inject constructor(
    private val details: network
) {
    //getting availabe data from teachers that have added like subjects etc
    suspend fun showInternalMarks(): DataorException<ShowInternalMarksResponse> {
        return try {

            DataorException.Loading(data = true)

            val response = details.showInternalMarks()

            DataorException.Success(data = response)

        }catch (ex:Exception){

            DataorException.Error(message = ex.message.toString())
        }
    }
}