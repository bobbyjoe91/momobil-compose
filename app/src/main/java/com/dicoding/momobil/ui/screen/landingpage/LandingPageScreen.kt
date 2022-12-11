package com.dicoding.momobil.ui.screen.landingpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.momobil.ui.theme.MomobilTheme
import com.dicoding.momobil.ui.theme.TaxiSoftRed

@Composable
fun LandingPageScreen(
  navigation: NavHostController = rememberNavController()
) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Landing Page Screen"
    )
    Button(
      onClick = {
        navigation.navigate("ProductDetail")
      },
      colors = ButtonDefaults.buttonColors(
        backgroundColor = TaxiSoftRed
      )
    ) {
      Text(
        "To Product Detail",
        color = Color.White
      )
    }
  }
}

@Preview(showBackground = true)
@Composable
fun LandingPageScreenPreview() {
  MomobilTheme {
    LandingPageScreen()
  }
}