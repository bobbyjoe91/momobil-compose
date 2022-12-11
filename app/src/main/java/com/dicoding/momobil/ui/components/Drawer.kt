package com.dicoding.momobil.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.dicoding.momobil.ui.theme.MomobilTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@Composable
fun Drawer(
  navigation: NavHostController? = null,
  scaffoldState: ScaffoldState? = null,
  coroutineScope: CoroutineScope? = null
) {
  Column(
    modifier = Modifier.fillMaxSize()
  ) {
    Box(
      modifier = Modifier
        .height(150.dp)
        .fillMaxWidth()
        .background(MaterialTheme.colors.primary)
    )

    Row(
      verticalAlignment = Alignment.CenterVertically,
      modifier = Modifier
        .clickable {
          navigation?.currentBackStackEntry?.destination?.route?.let { screenName ->
            if (screenName != "About") {
              navigation.navigate("About")
            }
          }

          coroutineScope?.launch {
            scaffoldState?.drawerState?.close()
          }
        }
        .fillMaxWidth()
        .padding(vertical = 12.dp, horizontal = 16.dp)
    ) {
      Icon(
        imageVector = Icons.Default.Info,
        contentDescription = "About Us",
        tint = Color.DarkGray
      )
      Spacer(modifier = Modifier.width(32.dp))
      Text(
        text = "About Us",
        style = MaterialTheme.typography.subtitle2
      )
    }

    Divider()
  }
}

@Preview(showBackground = true)
@Composable
fun DrawerPreview() {
  MomobilTheme {
    Drawer()
  }
}