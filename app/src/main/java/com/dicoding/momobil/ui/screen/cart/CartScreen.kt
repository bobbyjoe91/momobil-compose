package com.dicoding.momobil.ui.screen.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.momobil.di.Injector
import com.dicoding.momobil.model.Mobil
import com.dicoding.momobil.ui.ViewModelFactory
import com.dicoding.momobil.ui.common.UiState
import com.dicoding.momobil.ui.components.CartItem
import com.dicoding.momobil.ui.theme.MomobilTheme

@Composable
fun CartScreen(
  navigation: NavHostController = rememberNavController(),
  viewModel: CartViewModel = viewModel(
    factory = ViewModelFactory(
      cartRepo = Injector.injectCartRepository()
    )
  )
) {
  viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiStateValue ->
    when (uiStateValue) {
      is UiState.Loading -> {
        viewModel.showAllItems()
      }
      is UiState.Success -> {
        CartItemList(
          state = uiStateValue.data,
          onRemoveItem = { index: Int -> viewModel.removeFromCart(index) },
        )
      }
      is UiState.Error -> {

      }
    }
  }
}

@Composable
fun CartItemList(
  state: MutableList<Mobil>,
  onRemoveItem: (Int) -> Unit,
) {
  LazyColumn(
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    itemsIndexed(
      state,
      key = { _, product -> product.id }
    ) { index, item ->
      CartItem(
        name = item.name,
        imgUrl = item.images[0],
        location = item.location,
        price = item.price,
        onDelete = { onRemoveItem(index) }
      )
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