package com.example.studentapp.network

import com.example.studentapp.model.getMydetails.MydetailsResponse
import com.example.studentapp.model.login.LoginRequest
import com.example.studentapp.model.login.LoginResponse
import com.example.studentapp.utils.Constants
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import javax.inject.Singleton

@Singleton
interface network {

    //for login
    @POST(value = Constants.LOGIN_ENDPOINT)
    suspend fun LoginTeacher(
        @Body loginRequest: LoginRequest): LoginResponse

    //function to get my details
    @GET(value=Constants.my_details)
    suspend fun getmyDetails(): MydetailsResponse


}