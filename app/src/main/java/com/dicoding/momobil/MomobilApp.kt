package com.dicoding.momobil

import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.dicoding.momobil.ui.components.Drawer
import com.dicoding.momobil.ui.components.Header
import com.dicoding.momobil.ui.screen.about.AboutScreen
import com.dicoding.momobil.ui.screen.cart.CartScreen
import com.dicoding.momobil.ui.screen.landingpage.LandingPageScreen
import com.dicoding.momobil.ui.screen.productdetail.ProductDetailScreen
import com.dicoding.momobil.ui.theme.MomobilTheme

@Composable
fun MomobilApp(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController()
) {
  val scaffoldState = rememberScaffoldState()
  val coroutineScope = rememberCoroutineScope()
  val backStack = navController.currentBackStackEntryAsState()

  Scaffold(
    scaffoldState = scaffoldState,
    modifier = modifier,
    drawerContent = {
      Drawer(
        navigation = navController,
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope
      )
    },
    drawerGesturesEnabled = scaffoldState.drawerState.isOpen,
    topBar = {
      Header(
        currentRoute = backStack.value?.destination?.route ?: "",
        navigation = navController,
        scaffoldState = scaffoldState,
        coroutineScope = coroutineScope,
      )
    }
  ) {
    NavHost(
      navController = navController,
      startDestination = "LandingPage",
      modifier = Modifier.padding(it)
    ) {
      composable("LandingPage") {
        LandingPageScreen(
          navigation = navController
        )
      }
      composable(
        "ProductDetail/{id}",
        arguments = listOf(navArgument("id") { type = NavType.IntType })
      ) { backStack ->
        val productId = backStack.arguments?.getInt("id") ?: 0
        ProductDetailScreen(
          productId = productId,
          navigation = navController
        )
      }
      composable("About") {
        AboutScreen()
      }
      composable("Cart") {
        CartScreen(
          navigation = navController
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun MomobilAppPreview() {
  MomobilTheme {
    MomobilApp()
  }
}