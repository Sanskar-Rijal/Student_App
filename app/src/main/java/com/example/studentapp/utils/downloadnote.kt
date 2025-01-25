package com.example.studentapp.utils

import android.content.Context
import android.content.Intent
import android.net.Uri

fun downloadFile(context: Context, fileUrl: String) {
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(fileUrl))
    context.startActivity(intent)
}
