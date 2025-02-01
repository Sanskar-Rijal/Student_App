package com.example.studentapp.Screens.ShowTeacher

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil3.compose.AsyncImage
import coil3.request.ImageRequest
import coil3.request.crossfade
import com.example.studentapp.R
import com.example.studentapp.components.AppBarbySans
import com.example.studentapp.model.Teacher.Teacher
import com.example.studentapp.utils.getTeachers


@Composable
fun ShowTeacher(navController:NavController= NavController(LocalContext.current),
                getTeacher:List<Teacher> = getTeachers()
){
    Scaffold(
        topBar = {
            AppBarbySans(
                title = "Show Teachers",
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

                        LazyColumn(contentPadding = PaddingValues(10.dp)) {
                            items(getTeacher){
                                TeacherRow(teacher = it)
                            }
                    }
                }
            }
        }
    }
}



@Preview
@Composable
fun TeacherRow(teacher: Teacher= getTeachers()[0])
//onItemClick is of type string and it returns nothing , if we give = {} then
//we are saying at default we are not passing any thing
{
    var expand = remember {
        mutableStateOf(false)
    }

    Card(modifier = Modifier
        .fillMaxWidth()
        .padding(10.dp),
        //.height(130.dp)
        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp),
        shape = RoundedCornerShape(
            corner = CornerSize(15.dp)
        ),
        colors = CardDefaults.cardColors(Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                color = Color.White
//                shape = RectangleShape, shadowElevation = 10.dp
            ) {
//                Icon(imageVector = Icons.Default.AccountBox,
//                    contentDescription ="Image",
//                )
                AsyncImage(
                    model = ImageRequest.Builder(LocalContext.current)
                        .data(teacher.images)
                        .crossfade(true)
                        .build(),
                    placeholder = painterResource(R.drawable.dummy),
                    contentDescription = "image for movie",
                    contentScale = ContentScale.FillBounds,
                    modifier = Modifier.clip(CircleShape),
                    )
            }
            Column(modifier = Modifier.padding(3.dp)) {
                Text(
                    text = teacher.name,
                    style = MaterialTheme.typography.titleLarge,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "Email: ${teacher.email}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold
                )
                Text(text = "Phone No: ${teacher.phoneNo}",
                    style = MaterialTheme.typography.bodyMedium,
                    fontWeight = FontWeight.SemiBold)

                AnimatedVisibility(visible = expand.value) {

                    Column() {
                        //we need a state to know whether the down arrow has been clicked or not
                        Text(buildAnnotatedString {
                            /**
                            annoated string helps us to annotae each string
                            with different style
                            example "hello world" i can make helloo bold and world normal etc
                             **/
                            withStyle(
                                style = SpanStyle(
                                    //Color.DarkGray,
                                    fontSize = 13.sp
                                )
                            ) {
                                append("Description: ")
                            }
                            withStyle(
                                style = SpanStyle(
                                    // Color.DarkGray,
                                    fontSize = 13.sp,
                                    fontWeight = FontWeight.Light)){
                                append(teacher.description)
                            }
                        },
                            modifier = Modifier.padding(2.dp)
                        )
                        HorizontalDivider(
                            modifier = Modifier.padding(4.dp), thickness = 1.dp, color = Color.White
                        )


                        Column(modifier = Modifier.padding(3.dp)) {
                            Text(
                                text = "Name: ${teacher.name}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "email: ${teacher.email}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                            Text(
                                text = "Phone No: ${teacher.phoneNo}",
                                style = MaterialTheme.typography.bodyMedium
                            )
                        }
                    }
                }
                Icon(imageVector = if (expand.value) Icons.Filled.KeyboardArrowUp
                else
                    Icons.Filled.KeyboardArrowDown,
                    contentDescription = "small button",
                    modifier = Modifier
                        .size(30.dp)
                        .clickable {
                            expand.value = !expand.value
                        }

                )
            }
        }
    }
}