package com.example.studentapp.Screens.LoginScreen

data class LoadingState(
    var status:Status,
    var message:String?=null
){

    companion object{
        val IDLE = LoadingState(Status.IDLE)
        val FAILED= LoadingState(Status.FAILIED)
        val SUCCESS= LoadingState(Status.SUCCESS)
        val LOADING = LoadingState(Status.LOADING)
    }

    enum class Status{
        SUCCESS,
        FAILIED,
        LOADING,
        IDLE
    }
}