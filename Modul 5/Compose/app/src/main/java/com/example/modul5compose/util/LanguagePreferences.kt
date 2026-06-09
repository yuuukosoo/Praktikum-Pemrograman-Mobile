package com.example.modul5compose.util

import android.content.Context

class LanguagePreferences(context: Context) {
    private val prefs = context.getSharedPreferences("movie_prefs", Context.MODE_PRIVATE)

    fun saveLanguage(lang: String) { prefs.edit().putString("lang", lang).apply() }
    fun getLanguage(): String = prefs.getString("lang", "en-US") ?: "en-US"

    fun saveDarkMode(isDark: Boolean) { prefs.edit().putBoolean("dark_mode", isDark).apply() }
    fun isDarkMode(): Boolean = prefs.getBoolean("dark_mode", true)

    fun saveUsername(username: String) { prefs.edit().putString("username", username).apply() }
    fun getUsername(): String = prefs.getString("username", "Guest") ?: "Guest"
}