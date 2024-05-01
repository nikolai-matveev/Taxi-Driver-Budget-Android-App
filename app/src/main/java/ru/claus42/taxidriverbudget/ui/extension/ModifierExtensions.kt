package ru.claus42.taxidriverbudget.ui.extension

import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layout


fun Modifier.center(
    vertical: Float = 0.5f,
    horizontal: Float = 0.5f,
): Modifier =
    this then layout { measurable, constraints ->
        val placeable = measurable.measure(constraints)
        val y = ((constraints.maxHeight * vertical) - (placeable.height / 2)).toInt()
        val x = ((constraints.maxWidth - placeable.width) * horizontal).toInt()
        layout(placeable.width, placeable.height) {
            placeable.placeRelative(x, y)
        }
    }