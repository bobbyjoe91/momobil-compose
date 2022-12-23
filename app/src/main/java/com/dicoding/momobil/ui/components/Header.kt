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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.contentDescription
import androidx.compose.ui.semantics.semantics
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.momobil.R
import com.dicoding.momobil.navigation.Screen
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
        modifier = Modifier.semantics(mergeDescendants = true) {
          contentDescription = "toggle_drawer"
        },
        onClick = {
          coroutineScope.launch {
            scaffoldState.drawerState.open()
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
      if (
        currentRoute != Screen.About.routeName
        && currentRoute != Screen.Cart.routeName
      ) {
        IconButton(
          modifier = Modifier.semantics { contentDescription = "cart_page" },
          onClick = { navigation.navigate(Screen.Cart.routeName) }
        ) {
          Icon(
            imageVector = Icons.Default.ShoppingCart,
            tint = Color.Black,
            contentDescription = "cart_page"
          )
        }
      }
    }
  )
}