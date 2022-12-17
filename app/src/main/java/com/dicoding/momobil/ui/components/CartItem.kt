package com.dicoding.momobil.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.momobil.ui.common.Helpers
import com.dicoding.momobil.ui.theme.MomobilTheme
import com.dicoding.momobil.ui.theme.TaxiSoftRed

@Composable
fun CartItem(
  name: String,
  imgUrl: String,
  location: String,
  price: Int,
  onDelete: () -> Unit,
  modifier: Modifier = Modifier,
) {
  Card(
    modifier = modifier
      .height(100.dp)
      .fillMaxWidth(),
    shape = RoundedCornerShape(5.dp),
    border = BorderStroke(0.2.dp, Color.Black),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Row(
      modifier = modifier
        .fillMaxSize()
        .background(Color.White),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
      ) {
        AsyncImage(
          modifier = modifier
            .size(100.dp)
            .clip(RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp)),
          contentScale = ContentScale.FillHeight,
          model = imgUrl,
          contentDescription = "$name cart item"
        )
        Spacer(modifier = modifier.width(15.dp))
        Column {
          Text(name)
          Text(location)
          Text(Helpers.toCurrency(price))
        }
      }
      IconButton(
        onClick = onDelete,
      ) {
        Icon(
          modifier = modifier.size(30.dp),
          imageVector = Icons.Default.Delete,
          contentDescription = "Remove item",
          tint = TaxiSoftRed
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun CartItemPreview() {
  MomobilTheme {
    CartItem(
      name = "HONDA CR-V 1.5 PRESTIGE TRB",
      imgUrl = "https://res.cloudinary.com/adirafinance/image/upload/c_scale,h_560,dpr_auto,f_auto/media/products/prod/img-p1648433231874-1_datcfo",
      location = "BEKASI",
      price = 463000000,
      onDelete = {}
    )
  }
}