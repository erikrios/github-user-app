package io.erikrios.github.githubuserapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.erikrios.github.githubuserapp.repositories.UserRepository
import io.erikrios.github.githubuserapp.ui.viewstates.UserResponseViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SearchViewModel(private val repository: UserRepository) : ViewModel() {

    private val _viewState = MutableLiveData<UserResponseViewState>().apply {
        value = UserResponseViewState(loading = true)
    }

    val viewState: LiveData<UserResponseViewState>
        get() = _viewState

    fun searchUsers(keyword: String): Job {
        return viewModelScope.launch {
            _viewState.value = UserResponseViewState(loading = true)

            try {
                val response = repository.searchUsers(keyword)

                when {
                    response.isSuccessful -> {
                        _viewState.value = UserResponseViewState(
                            loading = false,
                            responseCode = response.code(),
                            userResponse = response.body()
                        )
                    }
                    else -> {
                        _viewState.value = UserResponseViewState(
                            loading = false,
                            responseCode = response.code(),
                            exception = Exception("Error to get data with response code ${response.code()}.")
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _viewState.value = UserResponseViewState(loading = false, exception = Exception(e))
            }
        }
    }
}