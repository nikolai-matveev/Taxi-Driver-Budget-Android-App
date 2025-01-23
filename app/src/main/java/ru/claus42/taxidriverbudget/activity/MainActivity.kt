package ru.claus42.taxidriverbudget.activity

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import ru.claus42.taxidriverbudget.navigation.RootNavHost
import ru.claus42.taxidriverbudget.ui.theme.TaxiDriverBudgetTheme
import ru.claus42.taxidriverbudget.utils.SetSystemNavigationBarColor

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        enableEdgeToEdge()

        setContent {
            TaxiBudgetApp()
        }
    }
}

@Composable
fun TaxiBudgetApp() {
    TaxiDriverBudgetTheme {
        SetSystemNavigationBarColor(MaterialTheme.colorScheme.surface)

        RootNavHost()
    }
}
