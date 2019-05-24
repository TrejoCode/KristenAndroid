package mx.edu.upqroo.kristenandroid.util

import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

object KristenDateUtils {
    fun formatDate(date: Date): String {
        return SimpleDateFormat("EEEE, d MMMM, yyyy", Locale.getDefault()).format(date)
    }
}
