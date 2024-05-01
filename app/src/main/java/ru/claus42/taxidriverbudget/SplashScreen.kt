package ru.claus42.taxidriverbudget

import androidx.compose.animation.Animatable
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.delay
import ru.claus42.taxidriverbudget.ui.extension.center

private val SplashTitleColor = Color(0xFFFBC803)
private const val ANIMATION_TIME = 2000

@Composable
fun SplashScreen(onSplashAnimationEnd: () -> Unit) {

    val bottomColor = remember { Animatable(Color.Black) }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    colors = listOf(Color.Black, bottomColor.value),
                    startY = 0.0f,
                    endY = Float.POSITIVE_INFINITY,
                )
            ),
    ) {
        AnimatedSplashTitle(modifier = Modifier.center(vertical = 0.4f))
        AnimatedLogo(modifier = Modifier.align(Alignment.BottomStart))
    }

    LaunchedEffect(true) {
        delay(ANIMATION_TIME.toLong())
        onSplashAnimationEnd()
    }

    LaunchedEffect(true) {
        bottomColor.animateTo(
            targetValue = Color.DarkGray,
            animationSpec = TweenSpec(durationMillis = ANIMATION_TIME)
        )
    }
}

@Composable
fun AnimatedSplashTitle(modifier: Modifier = Modifier) {
    val alpha = remember { Animatable(0f) }

    Text(
        text = stringResource(R.string.app_name),
        color = SplashTitleColor.copy(alpha = alpha.value),
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        modifier = modifier,
    )

    LaunchedEffect(true) {
        alpha.animateTo(
            targetValue = 1f,
            animationSpec = tween(ANIMATION_TIME)
        )
    }
}

@Composable
fun AnimatedLogo(modifier: Modifier = Modifier) {
    val painter = painterResource(id = R.drawable.logo)
    val logoWidth = painter.intrinsicSize.width

    val offsetX = remember { Animatable(-logoWidth) }

    LaunchedEffect(key1 = true) {
        offsetX.animateTo(
            targetValue = 0f,
            animationSpec = tween(ANIMATION_TIME, easing = FastOutSlowInEasing)
        )
    }

    Image(
        painter = painter,
        contentDescription = "Splash Screen Logo",
        modifier = modifier.offset(x = offsetX.value.dp)
    )
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen { }
}
