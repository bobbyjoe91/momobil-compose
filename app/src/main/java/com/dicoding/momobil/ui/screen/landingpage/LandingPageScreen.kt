package com.dicoding.momobil.ui.screen.landingpage

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.momobil.di.Injector
import com.dicoding.momobil.ui.ViewModelFactory
import com.dicoding.momobil.ui.common.UiState
import com.dicoding.momobil.ui.theme.MomobilTheme
import com.dicoding.momobil.ui.theme.TaxiSoftRed

@Composable
fun LandingPageScreen(
  modifier: Modifier = Modifier,
  navigation: NavHostController = rememberNavController(),
  viewModel: LandingPageViewModel = viewModel(
    factory = ViewModelFactory(Injector.injectProductRepository())
  )
) {
  when (
    val uiStateValue = viewModel.uiState.collectAsState(initial = UiState.Loading).value
  ) {
    is UiState.Loading -> {
      viewModel.getAllProducts()
    }
    is UiState.Success -> {
      println(uiStateValue.data)
      Column(
        modifier = modifier.fillMaxSize(),
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
    is UiState.Error -> {}
  }
}

@Preview(showBackground = true)
@Composable
fun LandingPageScreenPreview() {
  MomobilTheme {
    LandingPageScreen()
  }
}