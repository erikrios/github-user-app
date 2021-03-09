package io.erikrios.github.githubuserapp.ui.viewmodels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.erikrios.github.githubuserapp.datastrores.SettingPreferences
import io.erikrios.github.githubuserapp.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class ViewModelFactory : ViewModelProvider.Factory {

    private lateinit var repository: UserRepository
    private lateinit var username: String
    private var context: Context? = null
    private var preferences: SettingPreferences? = null

    constructor(repository: UserRepository, username: String) {
        this.repository = repository
        this.username = username
    }

    constructor(repository: UserRepository) {
        this.repository = repository
    }

    constructor(context: Context, preferences: SettingPreferences) {
        this.context = context
        this.preferences = preferences
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(DetailsViewModel::class.java) -> {
                DetailsViewModel(repository, username) as T
            }
            modelClass.isAssignableFrom(FavoritesViewModel::class.java) -> {
                FavoritesViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SearchViewModel::class.java) -> {
                SearchViewModel(repository) as T
            }
            modelClass.isAssignableFrom(SettingsViewModel::class.java) -> {
                SettingsViewModel(preferences ?: SettingPreferences(context as Context)) as T
            }
            else -> throw IllegalArgumentException()
        }
    }
}