package ru.claus42.taxidriverbudget.ui.drawables.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.PathData
import androidx.compose.ui.graphics.vector.group
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val CalendarIco: ImageVector
    get() {
        if (_CalendarIco != null) {
            return _CalendarIco!!
        }
        _CalendarIco = ImageVector.Builder(
            name = "RightButton",
            defaultWidth = 24.dp,
            defaultHeight = 24.dp,
            viewportWidth = 24f,
            viewportHeight = 24f
        ).apply {
            group(
                clipPathData = PathData {
                    moveTo(0f, 0f)
                    horizontalLineToRelative(24f)
                    verticalLineToRelative(24f)
                    horizontalLineToRelative(-24f)
                    close()
                }
            ) {
                path(
                    fill = SolidColor(Color(0xFF000000)),
                    pathFillType = PathFillType.EvenOdd
                ) {
                    moveTo(19.317f, 4.686f)
                    curveTo(18.612f, 3.981f, 17.908f, 3.626f, 17.003f, 3.419f)
                    verticalLineTo(2f)
                    curveTo(17.003f, 1.448f, 16.555f, 1f, 16.003f, 1f)
                    curveTo(15.451f, 1f, 15.003f, 1.448f, 15.003f, 2f)
                    verticalLineTo(3.152f)
                    curveTo(14.714f, 3.128f, 14.407f, 3.105f, 14.08f, 3.081f)
                    curveTo(13.402f, 3.031f, 12.703f, 3f, 12.003f, 3f)
                    curveTo(11.303f, 3f, 10.604f, 3.031f, 9.926f, 3.081f)
                    curveTo(9.598f, 3.105f, 9.292f, 3.128f, 9.003f, 3.152f)
                    verticalLineTo(2f)
                    curveTo(9.003f, 1.448f, 8.555f, 1f, 8.003f, 1f)
                    curveTo(7.451f, 1f, 7.003f, 1.448f, 7.003f, 2f)
                    verticalLineTo(3.419f)
                    curveTo(6.097f, 3.626f, 5.394f, 3.981f, 4.689f, 4.686f)
                    curveTo(3.376f, 5.999f, 3.279f, 7.307f, 3.084f, 9.923f)
                    curveTo(3.034f, 10.601f, 3.003f, 11.3f, 3.003f, 12f)
                    curveTo(3.003f, 12.7f, 3.034f, 13.399f, 3.084f, 14.077f)
                    curveTo(3.279f, 16.693f, 3.376f, 18.001f, 4.689f, 19.314f)
                    curveTo(6.002f, 20.627f, 7.31f, 20.724f, 9.926f, 20.919f)
                    curveTo(10.604f, 20.969f, 11.303f, 21f, 12.003f, 21f)
                    curveTo(12.703f, 21f, 13.402f, 20.969f, 14.08f, 20.919f)
                    curveTo(16.696f, 20.724f, 18.004f, 20.627f, 19.317f, 19.314f)
                    curveTo(20.63f, 18.001f, 20.727f, 16.693f, 20.922f, 14.077f)
                    curveTo(20.972f, 13.399f, 21.003f, 12.7f, 21.003f, 12f)
                    curveTo(21.003f, 11.3f, 20.972f, 10.601f, 20.922f, 9.923f)
                    curveTo(20.727f, 7.307f, 20.63f, 5.999f, 19.317f, 4.686f)
                    close()
                    moveTo(6f, 9f)
                    curveTo(5.448f, 9f, 5f, 9.448f, 5f, 10f)
                    curveTo(5f, 10.552f, 5.448f, 11f, 6f, 11f)
                    horizontalLineTo(18f)
                    curveTo(18.552f, 11f, 19f, 10.552f, 19f, 10f)
                    curveTo(19f, 9.448f, 18.552f, 9f, 18f, 9f)
                    horizontalLineTo(6f)
                    close()
                    moveTo(15f, 16.4f)
                    curveTo(15.773f, 16.4f, 16.4f, 15.773f, 16.4f, 15f)
                    curveTo(16.4f, 14.227f, 15.773f, 13.6f, 15f, 13.6f)
                    curveTo(14.227f, 13.6f, 13.6f, 14.227f, 13.6f, 15f)
                    curveTo(13.6f, 15.773f, 14.227f, 16.4f, 15f, 16.4f)
                    close()
                }
            }
        }.build()

        return _CalendarIco!!
    }

@Suppress("ObjectPropertyName")
private var _CalendarIco: ImageVector? = null
