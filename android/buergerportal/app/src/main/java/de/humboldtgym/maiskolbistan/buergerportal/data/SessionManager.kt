package de.humboldtgym.maiskolbistan.buergerportal.data

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Profile
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

class SessionManager(context: Context) {
    private val prefs: SharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    private val json = Json { ignoreUnknownKeys = true }

    var isLoggedIn: Boolean
        get() = prefs.getBoolean(KEY_LOGGED_IN, false)
        set(value) = prefs.edit { putBoolean(KEY_LOGGED_IN, value) }

    var profile: Profile?
        get() = prefs.getString(KEY_PROFILE, null)?.let { json.decodeFromString(it) }
        set(value) = prefs.edit {
            putString(KEY_PROFILE, value?.let { json.encodeToString(it) })
        }

    fun clear() {
        prefs.edit { clear() }
    }

    companion object {
        private const val PREFS_NAME = "buergerportal_session"
        private const val KEY_LOGGED_IN = "logged_in"
        private const val KEY_PROFILE = "profile"
    }
}
