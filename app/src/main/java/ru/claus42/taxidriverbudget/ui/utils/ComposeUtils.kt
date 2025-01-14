package ru.claus42.taxidriverbudget.ui.utils

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext

@Composable
fun SetSystemNavigationBarColor(color: Color) {
    val window = (LocalContext.current as? Activity)?.window
    SideEffect {
        window?.navigationBarColor = color.toArgb()
    }
}