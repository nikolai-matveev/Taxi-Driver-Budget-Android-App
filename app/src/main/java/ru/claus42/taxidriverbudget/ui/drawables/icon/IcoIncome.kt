package ru.claus42.taxidriverbudget.ui.drawables.icon

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PathFillType
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.path
import androidx.compose.ui.unit.dp

val IcoFinance: ImageVector
    get() {
        if (_IcoFinance != null) {
            return _IcoFinance!!
        }
        _IcoFinance = ImageVector.Builder(
            name = "IcoFinance",
            defaultWidth = 25.dp,
            defaultHeight = 25.dp,
            viewportWidth = 25f,
            viewportHeight = 25f
        ).apply {
            path(
                fill = SolidColor(Color(0xFF000000)),
                pathFillType = PathFillType.EvenOdd
            ) {
                moveTo(19.066f, 2.5f)
                horizontalLineTo(6.685f)
                curveTo(4.581f, 2.5f, 2.875f, 4.206f, 2.875f, 6.31f)
                verticalLineTo(18.691f)
                curveTo(2.875f, 20.794f, 4.581f, 22.5f, 6.685f, 22.5f)
                horizontalLineTo(19.066f)
                curveTo(21.169f, 22.5f, 22.875f, 20.794f, 22.875f, 18.691f)
                verticalLineTo(6.31f)
                curveTo(22.875f, 4.206f, 21.169f, 2.5f, 19.066f, 2.5f)
                close()
                moveTo(21.446f, 18.691f)
                curveTo(21.446f, 20.005f, 20.38f, 21.071f, 19.066f, 21.071f)
                horizontalLineTo(6.685f)
                curveTo(5.37f, 21.071f, 4.304f, 20.005f, 4.304f, 18.691f)
                verticalLineTo(6.31f)
                curveTo(4.304f, 4.995f, 5.37f, 3.929f, 6.685f, 3.929f)
                horizontalLineTo(19.066f)
                curveTo(20.38f, 3.929f, 21.446f, 4.995f, 21.446f, 6.31f)
                verticalLineTo(18.691f)
                close()
                moveTo(11.559f, 13.813f)
                horizontalLineTo(14.573f)
                curveTo(15.552f, 13.813f, 16.359f, 13.623f, 16.994f, 13.245f)
                curveTo(17.63f, 12.867f, 18.102f, 12.363f, 18.411f, 11.734f)
                curveTo(18.72f, 11.106f, 18.875f, 10.413f, 18.875f, 9.656f)
                curveTo(18.875f, 8.9f, 18.72f, 8.207f, 18.411f, 7.578f)
                curveTo(18.102f, 6.949f, 17.63f, 6.446f, 16.994f, 6.067f)
                curveTo(16.359f, 5.689f, 15.552f, 5.5f, 14.573f, 5.5f)
                horizontalLineTo(10.962f)
                curveTo(10.409f, 5.5f, 9.962f, 5.948f, 9.962f, 6.5f)
                verticalLineTo(12.309f)
                horizontalLineTo(8.627f)
                curveTo(8.212f, 12.309f, 7.875f, 12.645f, 7.875f, 13.061f)
                curveTo(7.875f, 13.476f, 8.212f, 13.813f, 8.627f, 13.813f)
                horizontalLineTo(9.962f)
                verticalLineTo(15.125f)
                horizontalLineTo(8.627f)
                curveTo(8.212f, 15.125f, 7.875f, 15.462f, 7.875f, 15.877f)
                curveTo(7.875f, 16.292f, 8.212f, 16.629f, 8.627f, 16.629f)
                horizontalLineTo(9.962f)
                verticalLineTo(18.701f)
                curveTo(9.962f, 19.142f, 10.319f, 19.5f, 10.76f, 19.5f)
                curveTo(11.201f, 19.5f, 11.559f, 19.142f, 11.559f, 18.701f)
                verticalLineTo(16.629f)
                horizontalLineTo(13.718f)
                curveTo(14.133f, 16.629f, 14.47f, 16.292f, 14.47f, 15.877f)
                curveTo(14.47f, 15.462f, 14.133f, 15.125f, 13.718f, 15.125f)
                horizontalLineTo(11.559f)
                verticalLineTo(13.813f)
                close()
                moveTo(11.559f, 12.309f)
                horizontalLineTo(14.573f)
                curveTo(15.191f, 12.309f, 15.702f, 12.195f, 16.106f, 11.967f)
                curveTo(16.509f, 11.734f, 16.81f, 11.42f, 17.007f, 11.023f)
                curveTo(17.205f, 10.622f, 17.304f, 10.167f, 17.304f, 9.656f)
                curveTo(17.304f, 9.146f, 17.205f, 8.692f, 17.007f, 8.296f)
                curveTo(16.81f, 7.895f, 16.509f, 7.58f, 16.106f, 7.353f)
                curveTo(15.702f, 7.12f, 15.191f, 7.004f, 14.573f, 7.004f)
                horizontalLineTo(11.559f)
                verticalLineTo(12.309f)
                close()
            }
        }.build()

        return _IcoFinance!!
    }

@Suppress("ObjectPropertyName")
private var _IcoFinance: ImageVector? = null
