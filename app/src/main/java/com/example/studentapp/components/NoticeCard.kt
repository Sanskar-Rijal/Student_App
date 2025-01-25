package com.example.studentapp.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.studentapp.model.Notices.Notice

@Composable
fun NoticeRow(
    data: Notice
){

    Card (modifier = Modifier
        .padding(10.dp)
        .clip(
            RoundedCornerShape(
                topEnd = 33.dp,
                bottomStart = 33.dp)
        )
        .fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {

        Column(modifier= Modifier
            .padding(horizontal = 14.dp, vertical = 6.dp),
            horizontalAlignment = Alignment.Start) {

            Text(text = data.title?:"No title",
                modifier= Modifier.padding(bottom = 5.dp),
                color = MaterialTheme.colorScheme.onBackground,
                style = TextStyle(
                    fontStyle = FontStyle.Normal,
                    fontSize = 20.sp
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
                    style = MaterialTheme.typography.bodySmall,
                    fontWeight = FontWeight.Normal,
                    color = Color.Gray
                    //color = Color.Black
                )
            }
        }
    }
}