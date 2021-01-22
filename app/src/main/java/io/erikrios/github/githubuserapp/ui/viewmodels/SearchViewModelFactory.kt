package io.erikrios.github.githubuserapp.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import io.erikrios.github.githubuserapp.repositories.UserRepository

@Suppress("UNCHECKED_CAST")
class SearchViewModelFactory(
    private val repository: UserRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(repository) as T
        }

        throw IllegalArgumentException()
    }
}