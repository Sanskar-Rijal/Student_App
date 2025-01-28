package com.example.studentapp.components

import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.studentapp.navigation.CampusConnectScreen

@Preview
@Composable
fun BottomBar(navController: NavController = NavController(LocalContext.current)){

    val navigationItem = listOf("Home","Notice")

    val selectedNavigationIndex = rememberSaveable {
        mutableIntStateOf(0)
    }
    NavigationBar(
        modifier = Modifier.height(70.dp),
        containerColor = Color(0xFF35474F),
        tonalElevation = 10.dp){
        navigationItem.forEachIndexed{index,text->

            NavigationBarItem(
                selected =false,
                onClick = {
                    selectedNavigationIndex.intValue=index
                    when(text){
                        "Home"->navController.navigate(CampusConnectScreen.HomeScreen.name)
                        else -> navController.navigate(CampusConnectScreen.ShowNoticesScreen.name)
                    }
                },
                icon = {
                    when(text){
                        "Home"-> Icon(
                            imageVector = Icons.Default.Home,
                            tint = Color.White,
                            contentDescription = "home screen"
                        )
                        else -> Icon(
                            imageVector = Icons.Default.Notifications,
                            tint = Color.White,
                            contentDescription = "Notification Screen"
                        )
                    }
                },
                label = {
                    Text(text=text,
                        fontSize = 15.sp,
                        color = Color.White,
                        fontWeight = FontWeight.ExtraBold)
                }
            )

        }
    }

}