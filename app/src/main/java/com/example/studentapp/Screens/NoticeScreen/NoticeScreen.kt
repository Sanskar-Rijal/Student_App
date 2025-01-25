package com.example.studentapp.Screens.NoticeScreen

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studentapp.R
import com.example.studentapp.Screens.AttendanceScreen.Showatt
import com.example.studentapp.Screens.LoginScreen.LoadingState
import com.example.studentapp.components.AppBarbySans
import com.example.studentapp.components.LoadingDialog
import com.example.studentapp.model.Notices.Notice
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
                    } else {
                        LazyColumn(
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            items(data.notices){ list->
                                NoticeRow(list)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun NoticeRow(
    data:Notice){

    Card (modifier = Modifier
        .padding(7.dp)
        .clip(
            RoundedCornerShape(
            topEnd = 33.dp,
            bottomStart = 33.dp)
        )
        .fillMaxWidth(),
        colors =CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(modifier=Modifier
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {

            Text(text = data.title?:"No title",
                modifier=Modifier.padding(bottom = 5.dp),
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontSize = 25.sp
                )
            )
            Text(text=data.description?:"No description" ,
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontSize = 15.sp
                )
            )
            Box(modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
                , contentAlignment = Alignment.TopEnd)
            {
                Text(
                    text =" ${data.teacher?:"no data"},subject-${data.subject?:"no data"}",
                    style = MaterialTheme.typography.bodySmall
                    //color = Color.Black
                )
            }
        }
    }
}