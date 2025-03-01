import android.app.DownloadManager
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
fun downloadFile(context: Context, fileUrl: String) {
    if (!isInternetAvailable(context)) {
        Toast.makeText(context, "No internet connection. Please connect to WiFi or Mobile Data.", Toast.LENGTH_LONG).show()
        return
    }

    // Generate a unique file name using timestamp
    val timeStamp = SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(Date())
    val fileName = "note_$timeStamp.pdf" // Example: note_20240305_153012.pdf

    val request = DownloadManager.Request(Uri.parse(fileUrl))
        .setTitle("Downloading PDF")
        .setDescription("Downloading your file...")
        .setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED)
        .setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, fileName)
        .setMimeType("application/pdf")

    val downloadManager = context.getSystemService(Context.DOWNLOAD_SERVICE) as DownloadManager
    downloadManager.enqueue(request) // Start downloading

    Toast.makeText(context, "Download started...", Toast.LENGTH_SHORT).show()
}

 // to check whether wifi is connected or not
fun isInternetAvailable(context: Context): Boolean {
    val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val network = connectivityManager.activeNetwork ?: return false
    val networkCapabilities = connectivityManager.getNetworkCapabilities(network) ?: return false

    return networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
            networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) // Checks for WiFi or Mobile Data
}
