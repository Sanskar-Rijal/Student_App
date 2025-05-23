package com.example.studentapp.Screens.InternalMarksScreen

import android.util.Log
import com.example.studentapp.Screens.AttendanceScreen.AttendanceScreenViewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studentapp.R
import com.example.studentapp.Screens.LoginScreen.LoadingState
import com.example.studentapp.components.AppBarbySans
import com.example.studentapp.components.LoadingDialog
import com.example.studentapp.components.ShowFailedText
import com.example.studentapp.model.ShowInternalMarks.Data
import com.example.studentapp.model.ShowInternalMarks.ShowInternalMarksResponse
import kotlin.math.log


@Composable
fun ShowInternalMarks(navController: NavController= NavController(LocalContext.current),
                   showInternalMarksviewmodel: InternalMarksViewmodel
){

    val data: ShowInternalMarksResponse = showInternalMarksviewmodel.item

    val uiState = showInternalMarksviewmodel.state.collectAsState()
    Scaffold(
        topBar = {
            AppBarbySans(
                title = "Internal Marks",
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
                    }else if(uiState.value == LoadingState.FAILED){
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center) {
                            ShowFailedText()
                        }
                    }
                    else {
                        if(data.data.isEmpty()){
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center) {
                                ShowFailedText()
                            }
                        }
                        LazyColumn(
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            items(data.data){ list->
                                showMarks(data=list)
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun showMarks(
    data:Data,
    size:Int=50,
    title:String="sans",
){
    Card(modifier = Modifier
        .height(79.dp)
        .fillMaxWidth()
        .padding(5.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ){
        Row(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween){
            Column {
                Text(
                    text = data.subject?:"no data",
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    lineHeight = 23.sp,
                    letterSpacing = 0.5.sp
                )

            }
            Text(
                text = buildAnnotatedString {
                    withStyle(
                        style = SpanStyle(
                            color = Color(0xFF1490CF), // Red color for total classes attended
                            fontWeight = FontWeight.Bold,
                            fontSize = 23.sp
                        )
                    ) {
                        append(data.marks?.toString() ?: "no data")
                    }
                    append("/") // Separator
                    withStyle(
                        style = SpanStyle(
                            color = Color.Gray, // Gray color for total classes conducted
                            fontWeight = FontWeight.Normal,
                            fontSize = 16.sp
                        )
                    ) {
                        append("20")
                    }
                },
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center,
                lineHeight = 23.sp
            )


        }
    }
}