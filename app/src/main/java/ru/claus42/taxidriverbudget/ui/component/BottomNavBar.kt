package ru.claus42.taxidriverbudget.ui.component

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.selection.selectableGroup
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.semantics.clearAndSetSemantics
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Composable
fun BottomNavBar(
    modifier: Modifier = Modifier,
    navBarTabs: List<NavBarTab>,
    onTabSelected: (NavBarTab) -> Unit,
    currentTab: NavBarTab
) {
    Surface(
        modifier = modifier
            .height(BottomBarHeight)
            .fillMaxWidth(),
        shadowElevation = RowTabElevation,
    ) {
        Row(
            modifier = Modifier
                .selectableGroup()
                .padding(
                    start = RowTabHorizontalPadding,
                    end = RowTabHorizontalPadding,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            navBarTabs.forEach { tab ->
                BottomNavBarTab(
                    text = stringResource(tab.labelResId),
                    icon = tab.icon,
                    onSelected = { onTabSelected(tab) },
                    selected = currentTab == tab
                )
            }
        }
    }
}

@Composable
fun BottomNavBarTab(
    text: String,
    icon: ImageVector,
    onSelected: () -> Unit,
    selected: Boolean,
) {

    val selectedTabColor = MaterialTheme.colorScheme.onSurfaceVariant
    val unselectedTabColor = MaterialTheme.colorScheme.onSurface

    val durationMillis = if (selected) TabFadeInAnimationDuration else TabFadeOutAnimationDuration
    val animSpec = remember {
        tween<Color>(
            durationMillis = durationMillis,
            easing = LinearEasing,
            delayMillis = TabFadeInAnimationDelay
        )
    }
    val tabTintColor by animateColorAsState(
        targetValue = if (selected) selectedTabColor else unselectedTabColor,
        animationSpec = animSpec
    )
    Column(
        modifier = Modifier
            .width(TabWidth)
            .clearAndSetSemantics { contentDescription = text }
            .selectable(
                selected = selected,
                onClick = onSelected,
                role = Role.Tab,
                interactionSource = remember { MutableInteractionSource() },
                indication = ripple(
                    bounded = false,
                    radius = Dp.Unspecified,
                    color = Color.Unspecified
                )
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Icon(imageVector = icon, contentDescription = text, tint = tabTintColor)
        Text(
            text = text,
            color = tabTintColor,
            style = MaterialTheme.typography.labelMedium,
            fontWeight = if (selected) FontWeight.Bold else FontWeight.Normal
        )
    }
}

@Preview
@Composable
fun PreviewBottomNavBar() {
    BottomNavBar(
        navBarTabs = bottomNavBarTabs,
        onTabSelected = {},
        currentTab = FinanceNavBarTab,
    )
}

private val RowTabHorizontalPadding = 20.dp
val BottomBarHeight = 56.dp
private val RowTabElevation = 12.dp
private val TabWidth = 80.dp

private const val TabFadeInAnimationDuration = 150
private const val TabFadeInAnimationDelay = 100
private const val TabFadeOutAnimationDuration = 100