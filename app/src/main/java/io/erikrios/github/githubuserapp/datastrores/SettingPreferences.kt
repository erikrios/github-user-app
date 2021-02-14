package io.erikrios.github.githubuserapp.datastrores

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.createDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class SettingPreferences(context: Context) {

    private val dataStore: DataStore<Preferences> =
        context.createDataStore(name = "setting_preferences")

    companion object {
        val NOTIFICATION_STATE_KEY = booleanPreferencesKey("notification_state")
    }

    suspend fun setNotificationState(isActive: Boolean) {
        dataStore.edit { preferences ->
            preferences[NOTIFICATION_STATE_KEY] = isActive
        }
    }

    val isNotificationActive: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[NOTIFICATION_STATE_KEY] ?: false
        }
}