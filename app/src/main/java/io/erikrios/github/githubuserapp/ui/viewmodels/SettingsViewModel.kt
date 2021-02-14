package io.erikrios.github.githubuserapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import io.erikrios.github.githubuserapp.datastrores.SettingPreferences
import io.erikrios.github.githubuserapp.receivers.AlarmReceiver
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SettingsViewModel(private val preferences: SettingPreferences) : ViewModel() {

    private var alarmReceiver = AlarmReceiver()

    fun setNotificationState(isActive: Boolean): Job {
        return viewModelScope.launch {
            preferences.setNotificationState(isActive)
        }
    }

    fun isNotificationActive(): LiveData<Boolean> {
        return preferences.isNotificationActive.asLiveData()
    }

    fun setReminderAlarm(context: Context, type: String, title: String, message: String) {
        alarmReceiver.setReminderAlarm(context, type, title, message)
    }

    fun cancelAlarm(context: Context, type: String) {
        alarmReceiver.cancelAlarm(context, type)
    }
}