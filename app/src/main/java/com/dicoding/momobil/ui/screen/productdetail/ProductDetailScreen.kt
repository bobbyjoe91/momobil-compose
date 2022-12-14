package com.dicoding.momobil.ui.screen.productdetail

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
import com.dicoding.momobil.ui.common.Helpers
import com.dicoding.momobil.ui.common.UiState
import com.dicoding.momobil.ui.theme.MomobilTheme
import com.dicoding.momobil.ui.theme.TaxiSoftRed

@Composable
fun ProductDetailScreen(
  productId: Int,
  modifier: Modifier = Modifier,
  navigation: NavHostController = rememberNavController(),
  viewModel: ProductDetailViewModel = viewModel(
    factory = ViewModelFactory(
      Injector.injectProductRepository(),
      Injector.injectCartRepository()
    )
  )
) {
  when(
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value
  ) {
    is UiState.Loading -> {
      viewModel.getProductById(productId)
    }
    is UiState.Success -> {
      val productDetail = uiState.data

      Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Text(text = uiState.data.name)
        Text(text = Helpers.toCurrency(uiState.data.price))
        Text(text = uiState.data.location)
        Text(text = uiState.data.sellerName)
        Button(
          onClick = {
            navigation.navigate("Cart")
            viewModel.purchaseCar(productDetail)
          },
          colors = ButtonDefaults.buttonColors(
            backgroundColor = TaxiSoftRed
          )
        ) {
          Text(
            "Ajukan Sekarang",
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
fun ProductDetailScreenPreview() {
  MomobilTheme {
    ProductDetailScreen(1)
  }
}