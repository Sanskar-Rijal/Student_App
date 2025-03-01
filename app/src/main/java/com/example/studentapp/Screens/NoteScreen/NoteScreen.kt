package com.example.studentapp.Screens.NoteScreen

import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.PictureAsPdf
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studentapp.R
import com.example.studentapp.Screens.LoginScreen.LoadingState
import com.example.studentapp.components.AppBarbySans
import com.example.studentapp.components.LoadingDialog
import com.example.studentapp.components.ShowFailedText
import com.example.studentapp.model.Notices.NoticeResponse
import com.example.studentapp.model.getNote.Note
import com.example.studentapp.model.getNote.ShowNoteResponse
import downloadFile

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun showNote(navController: NavController = NavController(LocalContext.current),
             shownoteviewmodel: NoteScreenViewmodel
){

    val data:ShowNoteResponse = shownoteviewmodel.item

    val context= LocalContext.current

    val uiState = shownoteviewmodel.state.collectAsState()


    Scaffold(
        topBar = {
            AppBarbySans(
                title = "Notes",
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
                    }
                    else {
                        if(data.notes.isEmpty()){
                            Box(
                                modifier = Modifier.fillMaxSize(),
                                contentAlignment = Alignment.Center) {
                                ShowFailedText()
                            }
                        }
                        LazyColumn(
                            contentPadding = PaddingValues(10.dp)
                        ) {
                            items(data.notes){ list->
                                ShowPdf(list){
                                    downloadFile(context = context, fileUrl = it)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun ShowPdf(
    data:Note,
    onClick:(String)->Unit
){
    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp)
        .clip(
            RoundedCornerShape(
                topEnd = 33.dp,
                bottomStart = 33.dp)),
       // elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ){
        Row(modifier = Modifier
            .padding(20.dp)
            .fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            Column(modifier = Modifier
                .padding(5.dp)
                .weight(1f)
            ) {

                Text(
                    text = data.title ?: "No name",
                    modifier = Modifier.padding(bottom = 5.dp),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.Black,
                    textAlign = TextAlign.Center,
                    lineHeight = 23.sp,
                    letterSpacing = 0.5.sp
                )

                Text(
                    text = "${data.teacherName?:"No name"},subject-${data.subjectName?:"No name"}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = Color.Gray.copy(0.7f),
                    textAlign = TextAlign.Center,
                    lineHeight = 23.sp,
                    letterSpacing = 0.5.sp
                )
            }
            IconButton(onClick = {
                onClick(data.fileUrl)
            },
                modifier = Modifier.wrapContentSize()) {
                Icon(imageVector = Icons.Default.Download,
                    contentDescription = "pdf", tint = Color.Black.copy(alpha = 0.4f))
            }
        }
    }
}