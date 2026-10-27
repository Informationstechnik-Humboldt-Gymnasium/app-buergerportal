package de.humboldtgym.maiskolbistan.buergerportal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.humboldtgym.maiskolbistan.buergerportal.R
import de.humboldtgym.maiskolbistan.buergerportal.data.model.Appointment
import de.humboldtgym.maiskolbistan.buergerportal.ui.viewmodel.PortalViewModel

@Composable
fun AppointmentsScreen(viewModel: PortalViewModel = viewModel()) {
    val state by viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.appointments),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        if (state.isLoading) {
            CircularProgressIndicator()
        }

        if (state.appointments.isEmpty() && !state.isLoading) {
            Text("Keine Termine vorhanden.", style = MaterialTheme.typography.bodyLarge)
        } else {
            LazyColumn {
                items(state.appointments) { item ->
                    ListItem(
                        headlineContent = { Text(item.title) },
                        supportingContent = { Text("${item.office} • ${item.date}, ${item.time} Uhr") },
                        overlineContent = { Text(item.ref) }
                    )
                }
            }
        }
    }
}
