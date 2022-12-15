package com.dicoding.momobil.ui.components

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
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Row(
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      AsyncImage(
        modifier = modifier
          .size(75.dp)
          .clip(RoundedCornerShape(topStart = 5.dp, bottomStart = 5.dp)),
        model = imgUrl,
        contentDescription = "$name cart item"
      )
      Column {
        Text(name)
        Text(location)
        Text(Helpers.toCurrency(price))
      }
      IconButton(onClick = onDelete) {
        Icon(
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