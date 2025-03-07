package com.example.studentapp.components
import androidx.compose.foundation.layout.Row
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBarbySans(
    title: String="Attendance",
    icon:ImageVector?=null,
    onBackArrowClicked:()->Unit={}
) {


    CenterAlignedTopAppBar(
        title = {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(text = title,
                    color = Color.Black,
                    style = TextStyle(fontWeight = FontWeight.Bold,
                        fontSize = 23.sp)
                )
            }
        },
        navigationIcon = {
            //navigation icon is shown at the front of the Screen

            if(icon!=null){
                IconButton(onClick = {
                    onBackArrowClicked.invoke()
                }){
                    Icon(imageVector = icon,
                        contentDescription = "back arrow",
                        tint = MaterialTheme.colorScheme.onPrimaryContainer)
                }
            }
        },
        colors = TopAppBarDefaults.topAppBarColors(Color(0xFFF5F4F4))
    )
}


