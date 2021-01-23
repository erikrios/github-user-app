package io.erikrios.github.githubuserapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.erikrios.github.githubuserapp.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class DetailsViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(DetailsViewModel::class.java)) {
            return DetailsViewModel(repository) as T
        }

        throw IllegalArgumentException()
    }
}