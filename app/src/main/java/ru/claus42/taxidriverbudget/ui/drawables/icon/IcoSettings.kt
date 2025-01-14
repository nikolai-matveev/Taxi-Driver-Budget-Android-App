package ru.claus42.taxidriverbudget.ui.drawables.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcoSettings: ImageVector
    get() {
        if (_IcoSettings != null) {
            return _IcoSettings!!
        }
        _IcoSettings = ImageVector.Builder(
            name = "IcoSettings",
            defaultWidth = 25.dp,
            defaultHeight = 25.dp,
            viewportWidth = 25f,
            viewportHeight = 25f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(18.316f, 2.5f)
                horizontalLineTo(5.935f)
                curveTo(3.831f, 2.5f, 2.125f, 4.206f, 2.125f, 6.31f)
                verticalLineTo(18.691f)
                curveTo(2.125f, 20.794f, 3.831f, 22.5f, 5.935f, 22.5f)
                horizontalLineTo(18.316f)
                curveTo(20.419f, 22.5f, 22.125f, 20.794f, 22.125f, 18.691f)
                verticalLineTo(6.31f)
                curveTo(22.125f, 4.206f, 20.419f, 2.5f, 18.316f, 2.5f)
                close()
                moveTo(20.696f, 18.691f)
                curveTo(20.696f, 20.005f, 19.63f, 21.071f, 18.316f, 21.071f)
                horizontalLineTo(5.935f)
                curveTo(4.62f, 21.071f, 3.554f, 20.005f, 3.554f, 18.691f)
                verticalLineTo(6.31f)
                curveTo(3.554f, 4.995f, 4.62f, 3.929f, 5.935f, 3.929f)
                horizontalLineTo(18.316f)
                curveTo(19.63f, 3.929f, 20.696f, 4.995f, 20.696f, 6.31f)
                verticalLineTo(18.691f)
                close()
                moveTo(12.125f, 8.25f)
                curveTo(12.125f, 6.731f, 13.356f, 5.5f, 14.875f, 5.5f)
                curveTo(16.394f, 5.5f, 17.625f, 6.731f, 17.625f, 8.25f)
                curveTo(17.625f, 9.769f, 16.394f, 11f, 14.875f, 11f)
                curveTo(13.356f, 11f, 12.125f, 9.769f, 12.125f, 8.25f)
                close()
                moveTo(14.875f, 7f)
                curveTo(14.185f, 7f, 13.625f, 7.56f, 13.625f, 8.25f)
                curveTo(13.625f, 8.94f, 14.185f, 9.5f, 14.875f, 9.5f)
                curveTo(15.565f, 9.5f, 16.125f, 8.94f, 16.125f, 8.25f)
                curveTo(16.125f, 7.56f, 15.565f, 7f, 14.875f, 7f)
                close()
                moveTo(8.875f, 5.5f)
                curveTo(9.289f, 5.5f, 9.625f, 5.836f, 9.625f, 6.25f)
                verticalLineTo(12.25f)
                curveTo(9.625f, 12.664f, 9.289f, 13f, 8.875f, 13f)
                curveTo(8.461f, 13f, 8.125f, 12.664f, 8.125f, 12.25f)
                verticalLineTo(6.25f)
                curveTo(8.125f, 5.836f, 8.461f, 5.5f, 8.875f, 5.5f)
                close()
                moveTo(15.625f, 12.25f)
                curveTo(15.625f, 11.836f, 15.289f, 11.5f, 14.875f, 11.5f)
                curveTo(14.461f, 11.5f, 14.125f, 11.836f, 14.125f, 12.25f)
                verticalLineTo(18.25f)
                curveTo(14.125f, 18.664f, 14.461f, 19f, 14.875f, 19f)
                curveTo(15.289f, 19f, 15.625f, 18.664f, 15.625f, 18.25f)
                verticalLineTo(12.25f)
                close()
                moveTo(6.125f, 16.25f)
                curveTo(6.125f, 14.731f, 7.356f, 13.5f, 8.875f, 13.5f)
                curveTo(10.394f, 13.5f, 11.625f, 14.731f, 11.625f, 16.25f)
                curveTo(11.625f, 17.769f, 10.394f, 19f, 8.875f, 19f)
                curveTo(7.356f, 19f, 6.125f, 17.769f, 6.125f, 16.25f)
                close()
                moveTo(8.875f, 15f)
                curveTo(8.185f, 15f, 7.625f, 15.56f, 7.625f, 16.25f)
                curveTo(7.625f, 16.94f, 8.185f, 17.5f, 8.875f, 17.5f)
                curveTo(9.565f, 17.5f, 10.125f, 16.94f, 10.125f, 16.25f)
                curveTo(10.125f, 15.56f, 9.565f, 15f, 8.875f, 15f)
                close()
            }
        }.build()

        return _IcoSettings!!
    }

@Suppress("ObjectPropertyName")
private var _IcoSettings: ImageVector? = null
