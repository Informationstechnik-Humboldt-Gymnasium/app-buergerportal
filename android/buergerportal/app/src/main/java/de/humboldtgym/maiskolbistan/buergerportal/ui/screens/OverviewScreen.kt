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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import de.humboldtgym.maiskolbistan.buergerportal.R

@Composable
fun OverviewScreen() {
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
        DashboardCard(title = "Nächster Termin", body = "Bürgeramt – 28.10.2026, 10:00 Uhr")
        DashboardCard(title = "Kontostand", body = "1.250 MK-Dollar")
        DashboardCard(title = "Neue Ankündigung", body = "Wichtige Meldung der Regierung")
        DashboardCard(title = "Aktive Umfrage", body = "Stadtpark-Umfrage noch 3 Tage offen")
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
