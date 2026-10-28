package de.humboldtgym.maiskolbistan.buergerportal.ui.viewmodel

import android.app.Application
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import de.humboldtgym.maiskolbistan.buergerportal.data.SessionManager
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Profile
import de.humboldtgym.maiskolbistan.buergerportal.data.repository.PortalRepository
import kotlinx.coroutines.launch

class AuthViewModel(application: Application) : AndroidViewModel(application) {
    private val repository by lazy { PortalRepository() }
    private val session = SessionManager(application)

    private val _uiState = mutableStateOf(AuthUiState())
    val uiState: State<AuthUiState> = _uiState

    init {
        if (session.isLoggedIn) {
            _uiState.value = _uiState.value.copy(isLoggedIn = true, profile = session.profile)
        }
    }

    fun signIn(email: String, password: String) {
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                repository.signIn(email, password)
                    .onSuccess { profile ->
                        session.isLoggedIn = true
                        session.profile = profile
                        _uiState.value = _uiState.value.copy(isLoading = false, isLoggedIn = true, profile = profile)
                    }
                    .onFailure { error ->
                        _uiState.value = _uiState.value.copy(isLoading = false, error = error.localizedMessage ?: "Anmeldung fehlgeschlagen")
                    }
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = "Fehler: ${e.localizedMessage}")
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            try { repository.signOut() } catch (_: Exception) {}
            session.clear()
            _uiState.value = AuthUiState(isLoggedIn = false)
        }
    }

    fun dismissError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                    ?: throw IllegalArgumentException("Application not available")
                AuthViewModel(application)
            }
        }
    }
}

data class AuthUiState(
    val isLoading: Boolean = false,
    val isLoggedIn: Boolean = false,
    val profile: Profile? = null,
    val error: String? = null
)
