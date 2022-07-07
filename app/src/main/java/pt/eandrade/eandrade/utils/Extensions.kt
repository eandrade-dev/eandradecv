package pt.eandrade.eandrade.utils

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.core.content.ContextCompat
import com.google.gson.Gson

fun openBrowserUrl(context: Context, url: String) {
    val intent = Intent(
        Intent.ACTION_VIEW,
        Uri.parse(url)
    )
    ContextCompat.startActivity(context, intent, null)
}

fun <T> String.fromJson(type: Class<T>): T {
    return Gson().fromJson(this, type)
}

fun <T> T.toJson(): String? {
    return Gson().toJson(this)
}