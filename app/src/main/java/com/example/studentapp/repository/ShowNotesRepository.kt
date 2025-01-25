package com.example.studentapp.repository

import com.example.studentapp.data.DataorException
import com.example.studentapp.model.Notices.NoticeResponse
import com.example.studentapp.model.getNote.ShowNoteResponse
import com.example.studentapp.network.network
import javax.inject.Inject

class ShowNotesRepository @Inject constructor(
    private val details: network
) {
    //getting availabe data from teachers that have added like subjects etc
    suspend fun showNote(): DataorException<ShowNoteResponse> {
        return try {

            DataorException.Loading(data = true)

            val response = details.showNotes()

            DataorException.Success(data = response)

        }catch (ex:Exception){

            DataorException.Error(message = ex.message.toString())
        }
    }
}