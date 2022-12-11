package com.dicoding.momobil.ui.screen.cart

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
fun CartScreen(
  navigation: NavHostController = rememberNavController()
) {
  Column(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Column(
      verticalArrangement = Arrangement.Center,
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      Text(
        text = "Cart Screen"
      )
      Button(
        onClick = {
          navigation.navigate("About")
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
}

@Preview(showBackground = true)
@Composable
fun CartScreenPreview() {
  MomobilTheme {
    CartScreen()
  }
}