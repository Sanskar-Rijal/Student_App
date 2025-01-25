package com.example.studentapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.studentapp.navigation.CampusConnectNavigation
import com.example.studentapp.ui.theme.StudentAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            myapp {
                CampusConnectNavigation()
            }
        }
    }
}

@Composable
fun myapp(content:@Composable ()->Unit){
    StudentAppTheme {
        content()
    }
}


