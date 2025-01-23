package ru.claus42.taxidriverbudget.feature.finance.screen.main.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import ru.claus42.taxidriverbudget.ui.theme.CheckboxCheckedColor

import java.util.UUID

//todo: complete
@Composable
fun CircularCheckbox(
    uuid: UUID,
    onChecked: (UUID, Boolean) -> Unit
) {
    var checked by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .size(24.dp)
            .clip(CircleShape)
            .background(if (checked) Color.Green else CheckboxCheckedColor)
            .clickable {
                checked = !checked
                onChecked(uuid, checked)
            },
        contentAlignment = Alignment.Center
    ) {
        if (checked) {
            Icon(
                imageVector = Icons.Default.Check,
                contentDescription = "Checked",
                tint = Color.White,
                modifier = Modifier.size(24.dp)
            )
        }
    }
}
