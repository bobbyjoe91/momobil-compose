package com.dicoding.momobil.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import com.google.accompanist.systemuicontroller.rememberSystemUiController

private val LightColorPalette = lightColors(
  primary = TaxiYellow,
  primaryVariant = TaxiDarkYellow,
  secondary = TaxiSoftRed

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
fun MomobilTheme(content: @Composable () -> Unit) {
  val systemUi = rememberSystemUiController()
  systemUi.setStatusBarColor(
    color = TaxiYellow,
    darkIcons = true
  )

  MaterialTheme(
    colors = LightColorPalette,
    typography = Typography,
    shapes = Shapes,
    content = content
  )
}