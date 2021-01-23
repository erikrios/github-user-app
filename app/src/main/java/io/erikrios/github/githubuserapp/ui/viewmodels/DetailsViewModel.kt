package io.erikrios.github.githubuserapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.erikrios.github.githubuserapp.repositories.UserRepository
import io.erikrios.github.githubuserapp.ui.viewstates.UserViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: UserRepository) : ViewModel() {
    private val _viewState = MutableLiveData<UserViewState>().apply {
        value = UserViewState(loading = true)
    }

    val viewState: LiveData<UserViewState>
        get() = _viewState

    fun getUserDetails(username: String): Job {
        return viewModelScope.launch {
            _viewState.value = UserViewState(loading = true)

            try {
                val response = repository.getUserDetails(username)

                when {
                    response.isSuccessful -> {
                        _viewState.value = UserViewState(
                            loading = false,
                            responseCode = response.code(),
                            user = response.body()
                        )
                    }
                    else -> {
                        _viewState.value = UserViewState(
                            loading = false,
                            responseCode = response.code(),
                            exception = Exception("Error to get data with response code ${response.code()}")
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _viewState.value = UserViewState(loading = false, exception = Exception(e))
            }
        }
    }
}