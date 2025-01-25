package com.example.studentapp.repository

import android.util.Log
import com.example.studentapp.data.DataorException
import com.example.studentapp.model.getMydetails.MydetailsResponse
import com.example.studentapp.network.network
import javax.inject.Inject

class getmyDetails_Repository @Inject constructor(
    private val details: network
) {
    //getting availabe data from teachers that have added like subjects etc
    suspend fun getmyDetails_Repository(): DataorException<MydetailsResponse> {
        return try {

            DataorException.Loading(data = true)

            val response = details.getmyDetails()

            Log.d("sangyog", "getSubjects: $response")

            DataorException.Success(data = response)

        }catch (ex:Exception){
            Log.d("sangyog", "getSubjects: ${ex.message}")
            DataorException.Error(message = ex.message.toString())
        }
    }
}