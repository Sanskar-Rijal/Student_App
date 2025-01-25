package com.example.studentapp.Screens.AttendanceScreen
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.util.fastCbrt
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.studentapp.Screens.LoginScreen.LoadingState
import com.example.studentapp.data.DataorException
import com.example.studentapp.model.ShowAttendance.ShowAttendanceResponse
import com.example.studentapp.repository.ShowAttendanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AttendanceScreenViewModel @Inject constructor(
    private val repository: ShowAttendanceRepository
):ViewModel() {

    var item:ShowAttendanceResponse by mutableStateOf(
        ShowAttendanceResponse(
            attendanceSummary = emptyList(),
            message = "",
            success = false,
            totalClasses = 0,
            totalPresentClasses = 0)
    )

    private val _state = MutableStateFlow(LoadingState.IDLE)

    val state = _state.asStateFlow()

    init {
        showAttendance()
    }

     fun  showAttendance(){
        fetchAttendance()
    }

    private fun fetchAttendance(){
        viewModelScope.launch {

            _state.value = LoadingState.LOADING

            try {

                val response = repository.showAttendance()

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

