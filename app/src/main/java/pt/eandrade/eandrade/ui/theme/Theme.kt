package pt.eandrade.eandrade.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = ColorDarkPrimary,
    primaryVariant = ColorDarkPrimaryVariant,
    secondary = ColorSecondary,
    onPrimary = Color.Black,
    onSecondary = Color.Black
)

private val LightColorPalette = lightColors(
    primary = ColorPrimary,
    primaryVariant = ColorPrimaryVariant,
    secondary = ColorSecondary,
    onPrimary = Color.White,
    onSecondary = Color.Black

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun EAndradeTheme(darkTheme: Boolean = isSystemInDarkTheme(),
                  content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}