package ru.claus42.taxidriverbudget.ui.modifier

import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.draw.drawWithContent
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

fun Modifier.leftFadingEdge(
    color: Color,
    isVisible: Boolean,
    width: Dp = 16.dp,
    spec: AnimationSpec<Dp>? = tween(500)
) = fadingEdge(FadeSide.LEFT, color = color, width = width, isVisible = isVisible, spec = spec)

fun Modifier.rightFadingEdge(
    color: Color,
    isVisible: Boolean,
    width: Dp = 16.dp,
    spec: AnimationSpec<Dp>? = tween(500)
) = fadingEdge(FadeSide.RIGHT, color = color, width = width, isVisible = isVisible, spec = spec)

fun Modifier.topFadingEdge(
    color: Color,
    isVisible: Boolean,
    width: Dp = 16.dp,
    spec: AnimationSpec<Dp>? = tween(500)
) = fadingEdge(FadeSide.TOP, color = color, width = width, isVisible = isVisible, spec = spec)

fun Modifier.bottomFadingEdge(
    color: Color,
    isVisible: Boolean,
    width: Dp = 16.dp,
    spec: AnimationSpec<Dp>? = tween(500)
) = fadingEdge(FadeSide.BOTTOM, color = color, width = width, isVisible = isVisible, spec = spec)

fun Modifier.fadingEdge(
    vararg sides: FadeSide,
    color: Color,
    width: Dp,
    isVisible: Boolean,
    spec: AnimationSpec<Dp>?
) = composed {
    require(width > 0.dp) { "Invalid fade width: Width must be greater than 0" }

    val animatedWidth = spec?.let {
        animateDpAsState(
            targetValue = if (isVisible) width else 0.dp,
            animationSpec = spec,
            label = "Fade width"
        ).value
    }

    drawWithContent {
        // Draw the content
        this@drawWithContent.drawContent()

        // Go through all provided sides
        sides.forEach { side ->
            // Get start and end gradient offsets
            val (start, end) = this.size.getFadeOffsets(side)

            // Define the static width
            val staticWidth = if (isVisible) width.toPx() else 0f
            // Define the final width
            val widthPx = animatedWidth?.toPx() ?: staticWidth

            // Calculate fraction based on view size
            val fraction = when(side) {
                FadeSide.LEFT, FadeSide.RIGHT -> widthPx / this.size.width
                FadeSide.BOTTOM, FadeSide.TOP -> widthPx / this.size.height
            }

            // Draw the gradient
            drawRect(
                brush = Brush.linearGradient(
                    0f to color,
                    fraction to Color.Transparent,
                    start = start,
                    end = end
                ),
                size = this.size
            )
        }
    }
}

fun Size.getFadeOffsets(side: FadeSide): Pair<Offset, Offset> {
    return when (side) {
        FadeSide.LEFT -> Offset.Zero to Offset(width, 0f)
        FadeSide.RIGHT -> Offset(width, 0f) to Offset.Zero
        FadeSide.BOTTOM -> Offset(0f, height) to Offset.Zero
        FadeSide.TOP -> Offset.Zero to Offset(0f, height)
    }
}

enum class FadeSide {
    LEFT, RIGHT, BOTTOM, TOP
}
