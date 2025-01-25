package com.example.studentapp.cookies

import android.content.Context
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class AppCookieJar(context: Context) : CookieJar {
    private val cookiePreferences = CookiePreferences(context)
    private val cookieStore = mutableMapOf<String, MutableList<Cookie>>()

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        cookieStore[url.host] = cookies.toMutableList()
        cookiePreferences.saveCookies(cookies) // Saves to SharedPreferences
    }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        return cookiePreferences.loadCookies() // Loads from SharedPreferences
    }

    fun clearCookies() {
        cookieStore.clear()
        cookiePreferences.clearCookies()
    }
}