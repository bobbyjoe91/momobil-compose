package com.dicoding.momobil.ui.screen.cart

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
        if (uiStateValue.data.isNotEmpty()) {
          CartItemList(
            modifier = modifier.fillMaxSize(),
            navigation = navigation,
            state = uiStateValue.data,
            onRemoveItem = { item: Mobil -> viewModel.removeFromCart(item) },
          )
        } else {
          Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
          ) {
            Image(
              painterResource(id = R.drawable.dream_car),
              contentDescription = "dream car",
              contentScale = ContentScale.Fit,
              modifier = modifier.size(250.dp)
            )
            Text(
              stringResource(id = R.string.empty_cart),
              fontSize = 16.sp,
              fontWeight = FontWeight.W700
            )
          }
        }
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
  state: MutableSet<Mobil>,
  onRemoveItem: (Mobil) -> Unit,
) {
  val cartContext = LocalContext.current
  val stateMutableList = state.toMutableList()

  LazyColumn(
    modifier = modifier.padding(horizontal = 10.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    contentPadding = PaddingValues(vertical = 10.dp),
  ) {
    itemsIndexed(
      stateMutableList,
      key = { _, product -> product.id }
    ) { index, item ->
      val deleteMessage = stringResource(id = R.string.delete_message, item.name)
      CartItem(
        name = item.name,
        imgUrl = item.images[0],
        location = item.location,
        price = item.price,
        onDelete = {
          onRemoveItem(item)
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

      if (index < stateMutableList.lastIndex) {
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