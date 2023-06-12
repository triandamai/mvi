/*
 * Copyright (c) 2023 trian.app.
 */

package app.trian.resepku.data.local

import android.content.SharedPreferences
import androidx.core.content.edit

class SharedPref(
    private val sharedPreferences: SharedPreferences
) {
    companion object {
        const val isLoggedInKey = "hsdracvjkd"
        const val tokenKey = "syrtfjykvsujgdf"
        const val languageKey = "dtfvdff"
    }

    fun setLanguage(lang: String) = sharedPreferences.edit {
        putString(languageKey, lang)
        apply()
    }

    fun getLanguage() = sharedPreferences.getString(languageKey, "").orEmpty()

    fun setIsLoggedIn(isLoggedIn: Boolean) = sharedPreferences.edit {
        putBoolean(isLoggedInKey, isLoggedIn)
        apply()
    }

    fun getIsLoggedIn(): Boolean =
        sharedPreferences.getBoolean(isLoggedInKey, false)

    fun setToken(token: String) = sharedPreferences.edit {
        putString(tokenKey, token)
        apply()
    }

    fun getToken(): String =
        sharedPreferences.getString(tokenKey, "")
            .orEmpty()

    fun setUserLoggedIn(
        token: String
    ) = sharedPreferences.edit {
        putBoolean(isLoggedInKey, true)
        putString(tokenKey, token)
        apply()
    }
}