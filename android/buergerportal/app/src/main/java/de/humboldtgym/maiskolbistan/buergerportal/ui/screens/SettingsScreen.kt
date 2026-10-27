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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.humboldtgym.maiskolbistan.buergerportal.R

@Composable
fun SettingsScreen(onLogout: () -> Unit) {
    var darkMode by remember { mutableStateOf(false) }
    var notifications by remember { mutableStateOf(true) }

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
        ListItem(
            headlineContent = { Text("Dunkelmodus") },
            trailingContent = { Switch(checked = darkMode, onCheckedChange = { darkMode = it }) }
        )
        ListItem(
            headlineContent = { Text("Mitteilungen") },
            trailingContent = { Switch(checked = notifications, onCheckedChange = { notifications = it }) }
        )
        HorizontalDivider(Modifier.padding(vertical = 8.dp))
        ListItem(headlineContent = { Text("Version") }, supportingContent = { Text("1.0.0") })
        ListItem(headlineContent = { Text("Datenschutz") })
        Spacer(modifier = Modifier.weight(1f))
        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(stringResource(R.string.logout))
        }
    }
}
