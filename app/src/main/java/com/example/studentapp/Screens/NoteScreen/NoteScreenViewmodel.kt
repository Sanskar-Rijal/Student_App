package com.example.studentapp.Screens.NoteScreen

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapp.Screens.LoginScreen.LoadingState
import com.example.studentapp.data.DataorException
import com.example.studentapp.model.Notices.NoticeResponse
import com.example.studentapp.model.getNote.ShowNoteResponse
import com.example.studentapp.repository.ShowNotesRepository
import com.example.studentapp.repository.ShowNoticeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteScreenViewmodel @Inject constructor(
    private val repository: ShowNotesRepository): ViewModel() {

    var item: ShowNoteResponse by mutableStateOf(
        ShowNoteResponse(
            notes = emptyList(),
            success = false
        )
    )

    private val _state = MutableStateFlow(LoadingState.IDLE)

    val state = _state.asStateFlow()

    init {
        showNotice()
    }

    fun  showNotice(){
        fetchNotice()
    }

    private fun fetchNotice(){
        viewModelScope.launch {

            _state.value = LoadingState.LOADING

            try {

                val response = repository.showNote()

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