package com.dicoding.momobil.ui.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.ScaffoldState
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.momobil.R
import com.dicoding.momobil.ui.theme.TaxiYellow
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Header(
  currentRoute: String,
  navigation: NavHostController = rememberNavController(),
  scaffoldState: ScaffoldState,
  coroutineScope: CoroutineScope,
) {
  CenterAlignedTopAppBar (
    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
      containerColor = TaxiYellow
    ),
    navigationIcon = {
      IconButton(
        onClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.open()
            println("currentpage: $currentRoute")
          }
        }
      ) {
        Icon(
          imageVector = Icons.Default.Menu,
          tint = Color.Black,
          contentDescription = "Drawer"
        )
      }
    },
    title = {
      Text(
        text = stringResource(id = R.string.app_name),
        color = Color.Black,
        fontWeight = FontWeight.W700,
        fontSize = 18.sp,
      )
    },
    actions = {
      if (currentRoute != "About" && currentRoute != "Cart") {
        IconButton(
          onClick = { navigation.navigate("Cart") }
        ) {
          Icon(
            imageVector = Icons.Default.ShoppingCart,
            tint = Color.Black,
            contentDescription = "Cart"
          )
        }
      }
    }
  )
}