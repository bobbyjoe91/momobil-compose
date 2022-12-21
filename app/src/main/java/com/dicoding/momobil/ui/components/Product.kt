package com.dicoding.momobil.ui.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.dicoding.momobil.ui.common.Helpers
import com.dicoding.momobil.ui.theme.MomobilTheme
import com.dicoding.momobil.ui.theme.TaxiDarkBlue
import com.dicoding.momobil.ui.theme.TaxiPaleTurquoise

@Composable
fun Product(
  name: String,
  imgUrl: String,
  location: String,
  price: Int,
  modifier: Modifier = Modifier
) {
  Card(
    shape = RoundedCornerShape(5.dp),
    border = BorderStroke(0.2.dp, Color.Black),
    elevation = CardDefaults.cardElevation(5.dp)
  ) {
    Column(
      horizontalAlignment = Alignment.CenterHorizontally
    ) {
      AsyncImage(
        model = imgUrl,
        contentDescription = name,
        contentScale = ContentScale.Crop,
        modifier = modifier
          .height(120.dp)
          .clip(
            RoundedCornerShape(
              topStart = 5.dp,
              topEnd = 5.dp
            )
          )
      )

      Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
          .fillMaxWidth()
          .background(Color.White)
          .padding(10.dp)
      ) {
        Text(
          name,
          maxLines = 1,
          overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier.height(5.dp))
        Text(
          location,
          color = TaxiPaleTurquoise,
        )
        Spacer(modifier.height(5.dp))
        Text(
          Helpers.toCurrency(price),
          color = TaxiDarkBlue,
          fontWeight = FontWeight.W700
        )
      }
    }
  }
}

@Preview(showBackground = true)
@Composable
fun ProductPreview() {
  MomobilTheme {
    Product(
      name = "HONDA CR-V 1.5 PRESTIGE TRB",
      imgUrl = "https://res.cloudinary.com/adirafinance/image/upload/c_scale,h_560,dpr_auto,f_auto/media/products/prod/img-p1648433231874-1_datcfo",
      location = "BEKASI",
      price = 463000000
    )
  }
}