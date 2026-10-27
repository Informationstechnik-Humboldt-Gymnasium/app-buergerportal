package de.humboldtgym.maiskolbistan.buergerportal.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.humboldtgym.maiskolbistan.buergerportal.R

data class Appointment(val title: String, val date: String, val time: String)

@Composable
fun AppointmentsScreen() {
    val appointments = listOf(
        Appointment("Bürgeramt", "28.10.2026", "10:00"),
        Appointment("Passverlängerung", "05.11.2026", "14:30"),
        Appointment("Steuerberatung", "12.11.2026", "09:00")
    )

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
        LazyColumn {
            items(appointments) { item ->
                ListItem(
                    headlineContent = { Text(item.title) },
                    supportingContent = { Text("${item.date}, ${item.time} Uhr") }
                )
            }
        }
    }
}
