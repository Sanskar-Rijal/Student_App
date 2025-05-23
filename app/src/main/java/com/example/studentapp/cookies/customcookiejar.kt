package com.example.studentapp.cookies

import android.content.Context
import com.example.studentapp.utils.Constants
import okhttp3.Cookie
import okhttp3.HttpUrl.Companion.toHttpUrl

class CookiePreferences(context: Context) {
    private val prefs = context.getSharedPreferences("cookie_prefs", Context.MODE_PRIVATE)

    fun saveCookies(cookies: List<Cookie>) {
        val editor = prefs.edit()
        val cookieStrings = cookies.map { it.toString() }
        editor.putStringSet("cookies", cookieStrings.toSet())
        editor.apply()
    }

    fun loadCookies(): List<Cookie> {
        val cookieStrings = prefs.getStringSet("cookies", emptySet()) ?: emptySet()
        return cookieStrings.mapNotNull {
            Cookie.parse(Constants.BASE_URL.toHttpUrl(), it)
        }
    }

    fun clearCookies() {
        prefs.edit().remove("cookies").apply()
    }
}