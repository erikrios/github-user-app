package io.erikrios.github.githubuserapp.ui.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import io.erikrios.github.githubuserapp.repositories.UserRepository
import io.erikrios.github.githubuserapp.ui.viewstates.UserViewState
import io.erikrios.github.githubuserapp.ui.viewstates.UsersViewState
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class DetailsViewModel(private val repository: UserRepository) : ViewModel() {
    private val _userViewState = MutableLiveData<UserViewState>().apply {
        value = UserViewState(loading = true)
    }

    val userViewState: LiveData<UserViewState>
        get() = _userViewState

    private val _followersViewState = MutableLiveData<UsersViewState>().apply {
        value = UsersViewState(loading = true)
    }

    val followersViewState: LiveData<UsersViewState>
        get() = _followersViewState

    private val _followingViewState = MutableLiveData<UsersViewState>().apply {
        value = UsersViewState(loading = true)
    }

    val followingViewState: LiveData<UsersViewState>
        get() = _followingViewState

    fun getUserDetails(username: String): Job {
        return viewModelScope.launch {
            _userViewState.value = UserViewState(loading = true)

            try {
                val response = repository.getUserDetails(username)

                when {
                    response.isSuccessful -> {
                        _userViewState.value = UserViewState(
                            loading = false,
                            responseCode = response.code(),
                            user = response.body()
                        )
                    }
                    else -> {
                        _userViewState.value = UserViewState(
                            loading = false,
                            responseCode = response.code(),
                            exception = Exception("Error to get data with response code ${response.code()}.")
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _userViewState.value = UserViewState(loading = false, exception = Exception(e))
            }
        }
    }

    fun getFollowers(username: String): Job {
        return viewModelScope.launch {
            _followersViewState.value = UsersViewState(loading = true)

            try {
                val response = repository.getFollowers(username)

                when {
                    response.isSuccessful -> {
                        _followersViewState.value = UsersViewState(
                            loading = false,
                            responseCode = response.code(),
                            users = response.body()
                        )
                    }
                    else -> {
                        _followersViewState.value = UsersViewState(
                            loading = false,
                            responseCode = response.code(),
                            exception = Exception("Error to get data with response code ${response.code()}.")
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _followersViewState.value =
                    UsersViewState(loading = false, exception = Exception(e))
            }
        }
    }

    fun getFollowing(username: String): Job {
        return viewModelScope.launch {
            _followingViewState.value = UsersViewState(loading = true)

            try {
                val response = repository.getFollowing(username)

                when {
                    response.isSuccessful -> {
                        _followingViewState.value = UsersViewState(
                            loading = false,
                            responseCode = response.code(),
                            users = response.body()
                        )
                    }
                    else -> {
                        _followingViewState.value = UsersViewState(
                            loading = false,
                            responseCode = response.code(),
                            exception = Exception("Error to get data with response code ${response.code()}.")
                        )
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _followingViewState.value =
                    UsersViewState(loading = false, exception = Exception(e))
            }
        }
    }
}