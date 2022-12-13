package com.dicoding.momobil.ui.screen.landingpage

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.momobil.di.Injector
import com.dicoding.momobil.ui.ViewModelFactory
import com.dicoding.momobil.ui.common.UiState
import com.dicoding.momobil.ui.components.Product
import com.dicoding.momobil.ui.theme.MomobilTheme
import com.dicoding.momobil.ui.theme.TaxiYellow

@Composable
fun LandingPageScreen(
  modifier: Modifier = Modifier,
  navigation: NavHostController = rememberNavController(),
  viewModel: LandingPageViewModel = viewModel(
    factory = ViewModelFactory(Injector.injectProductRepository())
  )
) {
  val (searchKeyword, setSearchKeyword) = remember { mutableStateOf("") }
  val focusRequester = remember { FocusRequester() }
  val focusManager = LocalFocusManager.current

  when (
    val uiStateValue = viewModel.uiState.collectAsState(initial = UiState.Loading).value
  ) {
    is UiState.Loading -> {
      viewModel.getAllProducts()
    }
    is UiState.Success -> {
      Column(
        modifier = modifier
          .fillMaxSize()
          .clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null
          ) { focusManager.clearFocus() },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
      ) {
        Box(
          contentAlignment = Alignment.Center,
          modifier = modifier
            .fillMaxWidth()
            .background(TaxiYellow)
            .padding(10.dp)
        ) {
          OutlinedTextField(
            value = searchKeyword,
            onValueChange = { input ->
              setSearchKeyword(input)
            },
            placeholder = { Text("Cari mobil impianmu") },
            singleLine = true,
            keyboardOptions = KeyboardOptions(
              keyboardType = KeyboardType.Text
            ),
            trailingIcon = {
              IconButton(
                onClick = {}
              ) {
                Icon(
                  imageVector = Icons.Default.Search,
                  tint = Color.Black,
                  contentDescription = "Search icon"
                )
              }
            },
            shape = RoundedCornerShape(5.dp),
            colors = TextFieldDefaults.textFieldColors(
              backgroundColor = Color.White,
              cursorColor = Color.Black,
            ),
            modifier = Modifier
              .fillMaxWidth()
              .focusRequester(focusRequester),
          )
        }

        LazyVerticalGrid(
          columns = GridCells.Adaptive(160.dp),
          contentPadding = PaddingValues(16.dp),
          horizontalArrangement = Arrangement.spacedBy(10.dp),
          verticalArrangement = Arrangement.spacedBy(10.dp),
          modifier = modifier
        ) {
          items(uiStateValue.data) { productData ->
            Product(
              name = productData.name,
              imgUrl = productData.images[0],
              location = productData.location,
              price = productData.price
            )
          }
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