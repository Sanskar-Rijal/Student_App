package com.example.studentapp.Screens.HomeScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Logout
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.FloatingActionButton
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
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
import com.example.studentapp.Screens.LoginScreen.LoginViewmodel
import com.example.studentapp.components.BottomBar
import com.example.studentapp.model.getMydetails.MydetailsResponse
import com.example.studentapp.navigation.CampusConnectScreen
import com.example.studentapp.utils.getRollno

@Composable
fun HomeScreen(navController: NavController= NavController(LocalContext.current),
               loginViewmodel: LoginViewmodel,
               homeScreenViewModel: HomeScreenViewModel
) {

    val details: MydetailsResponse = homeScreenViewModel.item

    val uiState = homeScreenViewModel.state.collectAsState()

    Scaffold(bottomBar = {
        BottomBar(navController = navController)
    }) { contentpadding ->

        Surface(
            modifier = Modifier
                .padding(contentpadding)
                .fillMaxSize(),
            color = Color(0xFF35474F)
        ) {

            Column {
                Spacer(modifier = Modifier.height(30.dp))

                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(10.dp),
                    horizontalArrangement = Arrangement.SpaceBetween) {

                    Icon(
                        imageVector = Icons.Rounded.AccountCircle,
                        modifier = Modifier
                            .absolutePadding(top = 10.dp)
                            .size(80.dp),
                        contentDescription = "User Icons",
                        tint = Color.White.copy(0.5f)
                    )

                    Column(modifier = Modifier
                        .padding(10.dp)
                        .weight(1f) // Take the remaining space in the Row
                    ) {
                        Text(
                            text = "Welcome !",
                            style = MaterialTheme.typography.labelMedium,
                            fontSize = 30.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF1490CF).copy(0.7f)
                        )
                        Text(
                            text = details.data.name ?: "No Name",
                            modifier = Modifier.padding(top = 10.dp),
                            fontSize = 23.sp,
                            color = Color.White
                        )
                        Text(
                            text = "(${details.data.email?:"no data"})",
                            modifier = Modifier.padding(top = 5.dp),
                            style = MaterialTheme.typography.bodyMedium,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))

//                    IconButton(modifier = Modifier.padding(top = 15.dp),
//                        onClick = {
//                            navController.navigate(CampusConnectScreen.ShowNoticesScreen.name)
//
//                        },) {
//                        Icon(
//                            modifier = Modifier.size(30.dp),
//                            imageVector = Icons.Default.Notifications,
//                            contentDescription = "Notification Icon",
//                            tint = Color.White)
//                    }
                    IconButton(modifier = Modifier.padding(start = 30.dp, top = 15.dp),
                        onClick = {
                            loginViewmodel.logoutTeacher()
                            navController.navigate(CampusConnectScreen.LoginScreen.name)}) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.Logout,
                            contentDescription = "LogOut",
                            tint = Color.White.copy(0.7f))
                    }


                }
                Spacer(modifier = Modifier.height(30.dp))
                BackgroundCardView(navController)
            }
        }
    }
}

//@Composable
//fun FloatingContent(onClick:(String)->Unit ){
//    FloatingActionButton(onClick={
//        onClick("")
//    },
//        shape = RoundedCornerShape(45.dp),
//        containerColor = MaterialTheme.colorScheme.tertiaryContainer,) {
//
//        Icon(
//            imageVector = Icons.AutoMirrored.Filled.Logout,
//            contentDescription = "LogOut",
//            tint = Color.Black.copy(alpha = 0.4f))
//    }
//}



@Composable
fun CardView(
    size:Int=50,
    icon:Painter,
    title:String,
    onClick: () -> Unit
){
    Card(modifier = Modifier
        .height(160.dp)
        .width(170.dp)
        .padding(10.dp)
        .clickable {
            onClick.invoke()
        },
        shape = RoundedCornerShape(20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ){
        Column(modifier = Modifier
            .padding(10.dp)
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){

            Icon(painter = icon,
                contentDescription = "Icon",
                modifier = Modifier.size(size.dp))

            Text(
                modifier = Modifier.padding(10.dp),
                text = title,
                style = MaterialTheme.typography.bodySmall,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                color = Color.Black,
                textAlign = TextAlign.Center,
                lineHeight = 23.sp
            )

        }
    }
}




@Composable
fun BackgroundCardView(navController: NavController) {
    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RoundedCornerShape(topStart = 20.dp, topEnd = 20.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Box {
            Image(
                painter = painterResource(R.drawable.home_page_bg),
                modifier = Modifier.fillMaxSize(),
                contentDescription = "background",
                contentScale = ContentScale.FillBounds)

            Column(modifier = Modifier
                .padding(10.dp)) {

                Spacer(modifier = Modifier.height(80.dp))

                Row(modifier = Modifier
                    .fillMaxWidth(),
                    horizontalArrangement =Arrangement.Absolute.SpaceEvenly) {
                    CardView(
                        title = "Attendance",
                        icon = painterResource(id = R.drawable.attendance_icon)
                    ){
                        navController.navigate(CampusConnectScreen.ShowAttendanceScreen.name)
                    }

                    CardView( title = "Notes",
                        icon = painterResource(id = R.drawable.notes_icon)){
                        navController.navigate(CampusConnectScreen.ShowNotesScreen.name)
                    }

                }

                Spacer(modifier = Modifier.height(50.dp))

                Row(modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement =Arrangement.Absolute.SpaceEvenly) {

                    CardView( title = "Show Teachers",
                        size = 40,
                        icon = painterResource(id = R.drawable.notice_icon)){
                        navController.navigate(CampusConnectScreen.ShowTeacherScreen.name)
                    }

                    CardView(
                        title = "Internal Marks",
                        size = 40,
                        icon = painterResource(id = R.drawable.internal_marks_icon)
                    ){
                        navController.navigate(CampusConnectScreen.ShowInternalMarksScreen.name)
                    }
                }


            }
        }
    }
}
