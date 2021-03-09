package io.erikrios.github.githubuserapp.utils

import android.content.Context
import androidx.datastore.preferences.preferencesDataStore

val Context.settingPreferences by preferencesDataStore("setting_preferences")
