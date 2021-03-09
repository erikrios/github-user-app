package io.erikrios.github.githubuserapp.datastrores

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import io.erikrios.github.githubuserapp.utils.settingPreferences
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences(private val context: Context) {

    companion object {
        val NOTIFICATION_STATE_KEY = booleanPreferencesKey("notification_state")
    }

    suspend fun setNotificationState(isActive: Boolean) {
        context.settingPreferences.edit { preferences ->
            preferences[NOTIFICATION_STATE_KEY] = isActive
        }
    }

    val isNotificationActive: Flow<Boolean> = context.settingPreferences.data
        .map { preferences ->
            preferences[NOTIFICATION_STATE_KEY] ?: false
        }
}