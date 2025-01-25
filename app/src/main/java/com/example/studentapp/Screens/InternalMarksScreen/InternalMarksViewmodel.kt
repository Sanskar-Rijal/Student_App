package com.example.studentapp.Screens.InternalMarksScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapp.Screens.LoginScreen.LoadingState
import com.example.studentapp.data.DataorException
import com.example.studentapp.model.ShowInternalMarks.ShowInternalMarksResponse
import com.example.studentapp.repository.ShowInternalMarksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class InternalMarksViewmodel @Inject constructor(
    private val repository: ShowInternalMarksRepository
):ViewModel() {

    var item:ShowInternalMarksResponse by mutableStateOf(
        ShowInternalMarksResponse(
            data= emptyList(),
            success = false
        )
    )

    private val _state = MutableStateFlow(LoadingState.IDLE)

    val state = _state.asStateFlow()

    init {
        showInternalMarks()
    }

    fun  showInternalMarks(){
        fetchMarks()
    }

    private fun fetchMarks(){
        viewModelScope.launch {

            _state.value = LoadingState.LOADING

            try {

                val response = repository.showInternalMarks()

                when(response){

                    is DataorException.Success -> {

                        item = response.data!!

                        if (item.success == true) {
                            _state.value = LoadingState.SUCCESS
                        }
                    }
                    is DataorException.Error -> {
                        _state.value = LoadingState.FAILED
                        _state.value.message = response.message
                        Log.d("april", "error: ${response.message} ")
                    }

                    else -> {
                        _state.value = LoadingState.IDLE
                    }
                }
            }catch (e:Exception){
                _state.value = LoadingState.FAILED
            }
        }
    }


}

