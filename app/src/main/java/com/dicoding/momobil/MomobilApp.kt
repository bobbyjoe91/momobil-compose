package com.dicoding.momobil

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.momobil.ui.theme.MomobilTheme

@Composable
fun MomobilApp(
  modifier: Modifier = Modifier,
  navController: NavHostController = rememberNavController()
) {

}

@Preview(showBackground = true)
@Composable
fun MomobilAppPreview() {
  MomobilTheme {
    MomobilApp()
  }
}