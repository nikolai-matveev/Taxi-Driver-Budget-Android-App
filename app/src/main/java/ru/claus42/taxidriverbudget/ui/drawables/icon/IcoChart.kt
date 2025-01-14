package ru.claus42.taxidriverbudget.ui.drawables.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcoChart: ImageVector
    get() {
        if (_IcoChart != null) {
            return _IcoChart!!
        }
        _IcoChart = ImageVector.Builder(
            name = "IcoGraphic",
            defaultWidth = 25.dp,
            defaultHeight = 25.dp,
            viewportWidth = 25f,
            viewportHeight = 25f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(6.435f, 2.5f)
                horizontalLineTo(18.816f)
                curveTo(20.919f, 2.5f, 22.625f, 4.206f, 22.625f, 6.31f)
                verticalLineTo(18.691f)
                curveTo(22.625f, 20.794f, 20.919f, 22.5f, 18.816f, 22.5f)
                horizontalLineTo(6.435f)
                curveTo(4.331f, 22.5f, 2.625f, 20.794f, 2.625f, 18.691f)
                verticalLineTo(6.31f)
                curveTo(2.625f, 4.206f, 4.331f, 2.5f, 6.435f, 2.5f)
                close()
                moveTo(18.816f, 21.071f)
                curveTo(20.13f, 21.071f, 21.196f, 20.005f, 21.196f, 18.691f)
                verticalLineTo(6.31f)
                curveTo(21.196f, 4.995f, 20.13f, 3.929f, 18.816f, 3.929f)
                horizontalLineTo(6.435f)
                curveTo(5.12f, 3.929f, 4.054f, 4.995f, 4.054f, 6.31f)
                verticalLineTo(18.691f)
                curveTo(4.054f, 20.005f, 5.12f, 21.071f, 6.435f, 21.071f)
                horizontalLineTo(18.816f)
                close()
            }
            path(fill = SolidColor(Color(0xFF000000))) {
                moveTo(17.863f, 9.405f)
                horizontalLineTo(15.006f)
                curveTo(14.611f, 9.405f, 14.292f, 9.725f, 14.292f, 10.119f)
                curveTo(14.292f, 10.514f, 14.611f, 10.833f, 15.006f, 10.833f)
                horizontalLineTo(15.892f)
                lineTo(12.501f, 14.224f)
                lineTo(10.596f, 12.319f)
                curveTo(10.091f, 11.815f, 9.273f, 11.815f, 8.768f, 12.319f)
                lineTo(6.282f, 14.729f)
                curveTo(6.004f, 15.007f, 6.004f, 15.459f, 6.282f, 15.738f)
                curveTo(6.415f, 15.873f, 6.597f, 15.948f, 6.787f, 15.948f)
                curveTo(6.977f, 15.95f, 7.159f, 15.874f, 7.292f, 15.738f)
                lineTo(9.644f, 13.386f)
                lineTo(11.549f, 15.29f)
                curveTo(12.057f, 15.795f, 12.878f, 15.795f, 13.387f, 15.29f)
                lineTo(17.111f, 11.567f)
                verticalLineTo(12.976f)
                curveTo(17.111f, 13.371f, 17.431f, 13.691f, 17.825f, 13.691f)
                curveTo(18.219f, 13.691f, 18.539f, 13.371f, 18.539f, 12.976f)
                verticalLineTo(10.119f)
                curveTo(18.535f, 9.741f, 18.24f, 9.43f, 17.863f, 9.405f)
                close()
            }
        }.build()

        return _IcoChart!!
    }

@Suppress("ObjectPropertyName")
private var _IcoChart: ImageVector? = null
