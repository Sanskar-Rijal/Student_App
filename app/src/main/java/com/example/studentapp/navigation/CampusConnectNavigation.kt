package com.example.studentapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.studentapp.Screens.AttendanceScreen.AttendanceScreenViewModel
import com.example.studentapp.Screens.AttendanceScreen.ShowAttendance
import com.example.studentapp.Screens.HomeScreen.HomeScreen
import com.example.studentapp.Screens.InternalMarksScreen.InternalMarksViewmodel
import com.example.studentapp.Screens.InternalMarksScreen.ShowInternalMarks
import com.example.studentapp.Screens.LoginScreen.LoginScreen
import com.example.studentapp.Screens.LoginScreen.LoginViewmodel
import com.example.studentapp.Screens.NoteScreen.NoteScreenViewmodel
import com.example.studentapp.Screens.NoteScreen.showNote
import com.example.studentapp.Screens.NoticeScreen.NoticeScreenViewmodel
import com.example.studentapp.Screens.NoticeScreen.ShowNotices
import kotlin.math.log

@Composable
fun CampusConnectNavigation() {

    val navController = rememberNavController()

    val loginviewmodel: LoginViewmodel = hiltViewModel()

    val domain = "sangyog-cc.vercel.app"

    LaunchedEffect(Unit) {
        loginviewmodel.checkStatusLoginStatus(domain)
        if (loginviewmodel.isLoggedIn) {
            navController.navigate(CampusConnectScreen.HomeScreen.name)
        }
    }

    NavHost(navController=navController,
        startDestination = CampusConnectScreen.LoginScreen.name) {

        composable(CampusConnectScreen.LoginScreen.name) {

            LoginScreen(navController, loginviewmodel)
        }

        composable(CampusConnectScreen.HomeScreen.name) {
            HomeScreen(
                navController = navController,
                loginViewmodel =  loginviewmodel,
                homeScreenViewModel = hiltViewModel()
            )
        }

        composable(CampusConnectScreen.ShowAttendanceScreen.name) {

            val showattendanceViewmodel = hiltViewModel<AttendanceScreenViewModel>()

            ShowAttendance(
                navController = navController,
                showAttendanceViewmodel = showattendanceViewmodel)
        }

        composable(CampusConnectScreen.ShowInternalMarksScreen.name) {

            val showInternalMarksViewmodel = hiltViewModel<InternalMarksViewmodel>()

            ShowInternalMarks(
                navController = navController,
                showInternalMarksviewmodel = showInternalMarksViewmodel)
        }

        composable(CampusConnectScreen.ShowNoticesScreen.name) {

            val shownoticeViewmodel = hiltViewModel<NoticeScreenViewmodel>()

            ShowNotices(
                navController = navController,
                showNotice = shownoticeViewmodel)
        }

        composable(CampusConnectScreen.ShowNotesScreen.name){
            val showNoteViewmodel = hiltViewModel<NoteScreenViewmodel>()

            showNote(
                navController=navController,
                shownoteviewmodel = showNoteViewmodel
            )

        }


    }
}