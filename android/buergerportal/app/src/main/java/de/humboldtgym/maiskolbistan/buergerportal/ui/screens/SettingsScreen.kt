package de.humboldtgym.maiskolbistan.buergerportal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import de.humboldtgym.maiskolbistan.buergerportal.R
import de.humboldtgym.maiskolbistan.buergerportal.ui.viewmodel.PortalViewModel

@Composable
fun SettingsScreen(
    onLogout: () -> Unit,
    viewModel: PortalViewModel = viewModel(factory = PortalViewModel.Factory)
) {
    var notifications by remember { mutableStateOf(true) }
    val state by viewModel.uiState

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(R.string.settings),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 12.dp)
        )

        state.profile?.let { profile ->
            ListItem(
                headlineContent = { Text("Angemeldet als") },
                supportingContent = { Text(profile.displayName ?: profile.email) }
            )
        }

        ListItem(
            headlineContent = { Text("Mitteilungen") },
            trailingContent = { Switch(checked = notifications, onCheckedChange = { notifications = it }) }
        )
        HorizontalDivider(Modifier.padding(vertical = 8.dp))
        ListItem(headlineContent = { Text("Version") }, supportingContent = { Text("1.0.0") })
        ListItem(headlineContent = { Text("Backend") }, supportingContent = { Text("Supabase") })
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.logout))
        }
    }
}
