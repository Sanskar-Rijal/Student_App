package com.example.studentapp.Screens.NoticeScreen

import android.util.Log
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studentapp.R
import com.example.studentapp.Screens.LoginScreen.LoadingState
import com.example.studentapp.components.AppBarbySans
import com.example.studentapp.components.BottomBar
import com.example.studentapp.components.LoadingDialog
import com.example.studentapp.components.NoticeRow
import com.example.studentapp.components.ShowFailedText
import com.example.studentapp.model.Notices.NoticeResponse


@Composable
fun ShowNotices(navController: NavController = NavController(LocalContext.current),
                   showNotice: NoticeScreenViewmodel){

    val data: NoticeResponse = showNotice.item

    val uiState = showNotice.state.collectAsState()


    Scaffold(
        topBar = {
            AppBarbySans(
                title = "Notice",
                icon = Icons.AutoMirrored.Filled.ArrowBack
            ) {
                navController.popBackStack()
            }
        },
        bottomBar = {
            BottomBar(navController)
        }
    ) { contentpadding ->
        Box {
            Image(
                painter = painterResource(R.drawable.home_page_bg),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "background",
                contentScale = ContentScale.FillBounds
            )

            Surface(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(contentpadding),
                color = Color.Transparent
            ) {

                Column(modifier = Modifier.padding(10.dp)) {

                    if (uiState.value == LoadingState.LOADING) {
                        LoadingDialog()
                    }
                    else {
                        LazyColumn(
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            items(data.notices){ list->
                                NoticeRow(list)
                            }
                        }
                    }
                    Log.d("Doraemon", "${uiState.value}: ")
                }
            }
        }
    }
}


