package ru.claus42.taxidriverbudget

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import ru.claus42.taxidriverbudget.navigation.RootNavHost
import ru.claus42.taxidriverbudget.ui.theme.TaxiDriverBudgetTheme
import ru.claus42.taxidriverbudget.ui.utils.SetSystemNavigationBarColor

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
