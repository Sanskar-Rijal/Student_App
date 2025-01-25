package com.example.studentapp.repository

import android.util.Log
import com.example.studentapp.data.DataorException
import com.example.studentapp.model.ShowAttendance.ShowAttendanceResponse
import com.example.studentapp.network.network
import javax.inject.Inject

class ShowAttendanceRepository @Inject constructor(
    private val details: network
) {
    //getting availabe data from teachers that have added like subjects etc
    suspend fun showAttendance(): DataorException<ShowAttendanceResponse> {
        return try {

            DataorException.Loading(data = true)

            val response = details.showAttendance()

            DataorException.Success(data = response)

        }catch (ex:Exception){

            DataorException.Error(message = ex.message.toString())
        }
    }
}