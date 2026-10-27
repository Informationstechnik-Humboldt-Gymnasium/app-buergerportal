package de.humboldtgym.maiskolbistan.buergerportal.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.humboldtgym.maiskolbistan.buergerportal.R
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Announcement
import de.humboldtgym.maiskolbistan.buergerportal.ui.viewmodel.PortalViewModel

@Composable
fun OverviewScreen(viewModel: PortalViewModel = viewModel()) {
    val state by viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Text(
            text = stringResource(R.string.overview),
            style = MaterialTheme.typography.headlineMedium
        )

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        state.profile?.let { profile ->
            DashboardCard(
                title = "Angemeldet als",
                body = profile.displayName ?: profile.email
            )
        }

        val nextAppointment = state.appointments.firstOrNull()
        DashboardCard(
            title = "Nächster Termin",
            body = nextAppointment?.let { "${it.title} – ${it.date}, ${it.time}" }
                ?: "Keine Termine vorhanden"
        )

        val latestAnnouncement = state.announcements.firstOrNull()
        DashboardCard(
            title = "Neueste Ankündigung",
            body = latestAnnouncement?.title ?: "Keine Ankündigungen"
        )

        DashboardCard(
            title = "Termine gesamt",
            body = "${state.appointments.size}"
        )
    }

    state.error?.let { error ->
        AlertDialog(
            onDismissRequest = viewModel::dismissError,
            title = { Text("Fehler") },
            text = { Text(error) },
            confirmButton = {
                TextButton(onClick = viewModel::dismissError) { Text("OK") }
            }
        )
    }
}

@Composable
private fun DashboardCard(title: String, body: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(title, style = MaterialTheme.typography.titleLarge, color = MaterialTheme.colorScheme.secondary)
            Spacer(Modifier.height(4.dp))
            Text(body, style = MaterialTheme.typography.bodyLarge)
        }
    }
}
