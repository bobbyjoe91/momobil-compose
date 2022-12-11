package com.dicoding.momobil

import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.dicoding.momobil.ui.screen.about.AboutScreen
import com.dicoding.momobil.ui.screen.cart.CartScreen
import com.dicoding.momobil.ui.screen.landingpage.LandingPageScreen
import com.dicoding.momobil.ui.screen.productdetail.ProductDetailScreen
import com.dicoding.momobil.ui.theme.MomobilTheme
import com.dicoding.momobil.ui.theme.TaxiYellow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MomobilApp(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController()
) {
  Scaffold(
    modifier = modifier,
    topBar = {
      CenterAlignedTopAppBar (
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = TaxiYellow
        ),
        navigationIcon = {
          IconButton(onClick = { Log.d("Clicked", "Open drawer") }) {
            Icon(
              imageVector = Icons.Default.Menu,
              tint = Color.Black,
              contentDescription = "Drawer"
            )
          }
        },
        title = {
          Text(
            text = "Momobil",
            color = Color.Black,
            fontWeight = FontWeight.W700,
            fontSize = 18.sp,
          )
        },
        actions = {
          IconButton(onClick = { Log.d("Clicked", "Open Cart") }) {
            Icon(
              imageVector = Icons.Default.ShoppingCart,
              tint = Color.Black,
              contentDescription = "Cart"
            )
          }
        }
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
      composable("ProductDetail") {
        ProductDetailScreen(
          navigation = navController
        )
      }
      composable("About") {
        AboutScreen(
          navigation = navController
        )
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