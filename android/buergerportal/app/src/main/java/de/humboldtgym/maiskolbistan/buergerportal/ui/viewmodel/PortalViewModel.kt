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
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Announcement
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Appointment
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Citizen
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Profile
import de.humboldtgym.maiskolbistan.buergerportal.data.repository.PortalRepository
import kotlinx.coroutines.launch

class PortalViewModel(application: Application) : AndroidViewModel(application) {
    private val repository by lazy { PortalRepository() }
    private val session = SessionManager(application)

    private val _uiState = mutableStateOf(PortalUiState())
    val uiState: State<PortalUiState> = _uiState

    init {
        loadAll()
    }

    fun loadAll() {
        val profile = session.profile ?: return
        _uiState.value = _uiState.value.copy(isLoading = true, error = null)
        viewModelScope.launch {
            try {
                val citizenResult = profile.citizen_id?.let { repository.getCitizen(it) }
                val appointmentsResult = repository.getAppointments(profile.citizen_id)
                val announcementsResult = repository.getAnnouncements()

                val errors = listOfNotNull(
                    citizenResult?.exceptionOrNull(),
                    appointmentsResult.exceptionOrNull(),
                    announcementsResult.exceptionOrNull()
                )

                _uiState.value = _uiState.value.copy(
                    isLoading = false,
                    profile = profile,
                    citizen = citizenResult?.getOrNull(),
                    appointments = appointmentsResult.getOrNull() ?: emptyList(),
                    announcements = announcementsResult.getOrNull() ?: emptyList(),
                    error = errors.firstOrNull()?.localizedMessage
                )
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(isLoading = false, error = "Fehler: ${e.localizedMessage}")
            }
        }
    }

    fun refresh() = loadAll()

    fun dismissError() {
        _uiState.value = _uiState.value.copy(error = null)
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY]
                    ?: throw IllegalArgumentException("Application not available")
                PortalViewModel(application)
            }
        }
    }
}

data class PortalUiState(
    val isLoading: Boolean = false,
    val profile: Profile? = null,
    val citizen: Citizen? = null,
    val appointments: List<Appointment> = emptyList(),
    val announcements: List<Announcement> = emptyList(),
    val error: String? = null
)
