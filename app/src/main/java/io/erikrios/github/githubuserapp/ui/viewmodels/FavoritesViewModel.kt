package io.erikrios.github.githubuserapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.erikrios.github.githubuserapp.repositories.UserRepository
import io.erikrios.github.githubuserapp.ui.viewstates.UsersViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class FavoritesViewModel(private val repository: UserRepository) : ViewModel() {
    private val _usersViewState = MutableLiveData<UsersViewState>().apply {
        value = UsersViewState(loading = true)
    }

    val usersViewState: LiveData<UsersViewState> get() = _usersViewState

    init {
        getFavoriteUsers()
    }

    private fun getFavoriteUsers(): Job {
        return viewModelScope.launch {
            _usersViewState.value = UsersViewState(loading = true)

            val users = repository.getUsers()
            _usersViewState.value = UsersViewState(loading = false, users = users)
        }
    }
}