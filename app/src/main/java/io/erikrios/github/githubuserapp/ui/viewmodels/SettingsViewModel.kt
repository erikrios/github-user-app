package io.erikrios.github.githubuserapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.erikrios.github.githubuserapp.datastrores.SettingPreferences
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SettingsViewModel(private val preferences: SettingPreferences) : ViewModel() {

    fun setNotificationState(isActive: Boolean): Job {
        return viewModelScope.launch {
            preferences.setNotificationState(isActive)
        }
    }

    fun isNotificationActive(): LiveData<Boolean> {
        return preferences.isNotificationActive.asLiveData()
    }
}