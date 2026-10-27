package de.humboldtgym.maiskolbistan.buergerportal.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import de.humboldtgym.maiskolbistan.buergerportal.R
import de.humboldtgym.maiskolbistan.buergerportal.ui.theme.Gold400
import de.humboldtgym.maiskolbistan.buergerportal.ui.theme.Maroon700
import de.humboldtgym.maiskolbistan.buergerportal.ui.theme.Parchment50

@Composable
fun IdCardScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.id_card),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = Maroon700)
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Text(
                    text = "REPUBLIK MAISKOLBISTAN",
                    color = Gold400,
                    style = MaterialTheme.typography.labelLarge,
                    fontWeight = FontWeight.Bold
                )
                Box(
                    modifier = Modifier
                        .size(120.dp)
                        .background(Parchment50),
                    contentAlignment = Alignment.Center
                ) {
                    Text("QR", color = Maroon700, fontWeight = FontWeight.Bold)
                }
                Text("Max Mustermann", color = Parchment50, style = MaterialTheme.typography.titleLarge)
                Text("Bürger-ID: MK-123456", color = Parchment50)
                Text("Geboren: 01.01.2005", color = Parchment50)
            }
        }
        Spacer(Modifier.height(24.dp))
        Text(
            text = "Platzhalter: QR-Code wird später dynamisch generiert.",
            style = MaterialTheme.typography.bodyLarge
        )
    }
}
