package de.humboldtgym.maiskolbistan.buergerportal

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import de.humboldtgym.maiskolbistan.buergerportal.ui.screens.AppointmentsScreen
import de.humboldtgym.maiskolbistan.buergerportal.ui.screens.IdCardScreen
import de.humboldtgym.maiskolbistan.buergerportal.ui.screens.LoginScreen
import de.humboldtgym.maiskolbistan.buergerportal.ui.screens.OverviewScreen
import de.humboldtgym.maiskolbistan.buergerportal.ui.screens.SettingsScreen
import de.humboldtgym.maiskolbistan.buergerportal.ui.theme.BuergerportalTheme
import de.humboldtgym.maiskolbistan.buergerportal.ui.viewmodel.AuthViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BuergerportalTheme {
                BuergerportalApp()
            }
        }
    }
}

sealed class Screen(val route: String, val labelRes: Int, val icon: @Composable () -> Unit) {
    object Overview : Screen("overview", R.string.overview, { Icon(Icons.Default.Home, null) })
    object Appointments : Screen("appointments", R.string.appointments, { Icon(Icons.Default.CalendarToday, null) })
    object IdCard : Screen("idcard", R.string.id_card, { Icon(Icons.Default.Person, null) })
    object Settings : Screen("settings", R.string.settings, { Icon(Icons.Default.Settings, null) })
}

@Composable
fun BuergerportalApp(authViewModel: AuthViewModel = viewModel()) {
    val navController = rememberNavController()
    val items = listOf(Screen.Overview, Screen.Appointments, Screen.IdCard, Screen.Settings)
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination
    val showBottomBar = currentDestination?.route in items.map { it.route }

    val authState by authViewModel.uiState

    when {
        authState.isLoading -> {
            CircularProgressIndicator(modifier = Modifier.fillMaxSize())
        }
        !authState.isLoggedIn -> {
            LoginScreen(
                onLogin = { email, password -> authViewModel.signIn(email, password) },
                isLoading = authState.isLoading,
                error = authState.error,
                onDismissError = authViewModel::dismissError
            )
        }
        else -> {
            Scaffold(
                modifier = Modifier.fillMaxSize(),
                bottomBar = {
                    if (showBottomBar) {
                        NavigationBar {
                            items.forEach { screen ->
                                NavigationBarItem(
                                    icon = { screen.icon() },
                                    label = { Text(stringResource(screen.labelRes)) },
                                    selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                                    onClick = {
                                        navController.navigate(screen.route) {
                                            popUpTo(navController.graph.findStartDestination().id) {
                                                saveState = true
                                            }
                                            launchSingleTop = true
                                            restoreState = true
                                        }
                                    }
                                )
                            }
                        }
                    }
                }
            ) { innerPadding ->
                NavHost(
                    navController = navController,
                    startDestination = Screen.Overview.route,
                    modifier = Modifier.padding(innerPadding)
                ) {
                    composable(Screen.Overview.route) { OverviewScreen() }
                    composable(Screen.Appointments.route) { AppointmentsScreen() }
                    composable(Screen.IdCard.route) { IdCardScreen() }
                    composable(Screen.Settings.route) { SettingsScreen(onLogout = { authViewModel.signOut() }) }
                }
            }
        }
    }
}
