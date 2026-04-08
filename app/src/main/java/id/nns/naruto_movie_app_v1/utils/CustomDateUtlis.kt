package id.nns.naruto_movie_app_v1.utils

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

object CustomDateUtils {
    @RequiresApi(Build.VERSION_CODES.O)
    fun formatDate(dateString: String): String {
        return try {
            val inputFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy")
            val outputFormatter = DateTimeFormatter.ofPattern("MMMM d, yyyy")
            val date = LocalDate.parse(dateString, inputFormatter)
            date.format(outputFormatter)
        } catch (_: Exception) {
            dateString
        }
    }
}
