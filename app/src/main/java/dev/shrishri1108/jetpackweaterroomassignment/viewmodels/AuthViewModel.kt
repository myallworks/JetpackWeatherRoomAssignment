package dev.shrishri1108.jetpackweaterroomassignment.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dev.shrishri1108.jetpackweaterroomassignment.repository.AuthRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val authRepository: AuthRepository
) : ViewModel() {

    val isLoggedIn: StateFlow<Boolean> = authRepository.isLoggedIn.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = false
    )

    val loggedInUserEmail: StateFlow<String?> = authRepository.loggedInUserEmail.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = null
    )

    fun login(username: String, password: String, isInvalid: () -> Unit) {
        viewModelScope.launch {
            if(!authRepository.login(username, password))isInvalid()
        }
    }

    fun logout(loggedOut: () -> Unit) {
        viewModelScope.launch(Dispatchers.Main) {
            authRepository.logout()
            withContext(coroutineContext) {
                loggedOut()
            }
        }
    }
}
