package com.dicoding.momobil.ui.screen.cart

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.momobil.R
import com.dicoding.momobil.di.Injector
import com.dicoding.momobil.model.Mobil
import com.dicoding.momobil.ui.ViewModelFactory
import com.dicoding.momobil.ui.common.UiState
import com.dicoding.momobil.ui.components.CartItem
import com.dicoding.momobil.ui.theme.MomobilTheme

@Composable
fun CartScreen(
  modifier: Modifier = Modifier,
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
          modifier = modifier
            .fillMaxSize(),
          navigation = navigation,
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
  modifier: Modifier,
  navigation: NavHostController,
  state: MutableList<Mobil>,
  onRemoveItem: (Int) -> Unit,
) {
  val cartContext = LocalContext.current

  LazyColumn(
    modifier = modifier.padding(horizontal = 10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    contentPadding = PaddingValues(vertical = 10.dp),
  ) {
    itemsIndexed(
      state,
      key = { _, product -> product.id }
    ) { index, item ->
      val deleteMessage = stringResource(id = R.string.delete_message, item.name)
      CartItem(
        name = item.name,
        imgUrl = item.images[0],
        location = item.location,
        price = item.price,
        onDelete = {
          onRemoveItem(index)
          Toast.makeText(cartContext, deleteMessage, Toast.LENGTH_SHORT).show()
        },
        onPress = {
          navigation.popBackStack()
          navigation.navigate("ProductDetail/${item.id}") {
            popUpTo("LandingPage") { saveState = true }
            launchSingleTop = true
            restoreState = true
          }
        }
      )

      if (index < state.lastIndex) {
        Spacer(modifier = Modifier.height(10.dp))
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