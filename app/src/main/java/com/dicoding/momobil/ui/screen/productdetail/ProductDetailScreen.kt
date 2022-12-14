package com.dicoding.momobil.ui.screen.productdetail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.dicoding.momobil.di.Injector
import com.dicoding.momobil.ui.ViewModelFactory
import com.dicoding.momobil.ui.common.Helpers
import com.dicoding.momobil.ui.common.UiState
import com.dicoding.momobil.ui.theme.MomobilTheme
import com.dicoding.momobil.ui.theme.TaxiSoftRed

@Composable
fun ProductDetailScreen(
  productId: Int,
  modifier: Modifier = Modifier,
  navigation: NavHostController = rememberNavController(),
  viewModel: ProductDetailViewModel = viewModel(
    factory = ViewModelFactory(
      Injector.injectProductRepository(),
      Injector.injectCartRepository()
    )
  )
) {
  when(
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value
  ) {
    is UiState.Loading -> {
      viewModel.getProductById(productId)
    }
    is UiState.Success -> {
      val productDetail = uiState.data

      Column(
        modifier = modifier
          .fillMaxSize()
          .background(color = Color(0xFFECECEC))
          .verticalScroll(rememberScrollState()),
      ) {
        Column(
          modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
          Text(
            text = uiState.data.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
          )
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            "Nominal Harga",
            color = Color(0xFFA0A0A0)
          )
          Spacer(modifier = Modifier.height(5.dp))
          Text(
            text = Helpers.toCurrency(uiState.data.price),
            color = Color(0xFF4379B2),
            fontSize = 18.sp,
            fontWeight = FontWeight.W700
          )
        }
        
        Spacer(modifier = modifier.height(7.dp))

        Row(
          verticalAlignment = Alignment.CenterVertically,
          modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 24.dp, vertical = 14.dp)
        ) {
          Icon(
            imageVector = Icons.Default.AccountCircle,
            contentDescription = "Seller profile",
            modifier = Modifier.size(48.dp)
          )
          Spacer(modifier = Modifier.width(14.dp))

          Column {
            Text(
              text = uiState.data.sellerName,
              fontWeight = FontWeight.W700,
              fontSize = 16.sp
            )
            Text(
              text = uiState.data.location,
              color = Color(0xFFA0A0A0),
              fontSize = 14.sp
            )
          }
        }

        Spacer(modifier = modifier.height(7.dp))

        Text(
          "Spesifikasi Mobil",
          fontWeight = FontWeight.W700,
          fontSize = 14.sp,
          modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 24.dp, vertical = 20.dp)
        )
        Divider()
        Column(
          modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
          Specification(
            title = "Warna",
            data = uiState.data.color
          )
          Specification(
            title = "Tahun Produksi",
            data = uiState.data.year.toString()
          )
          Specification(
            title = "Transmisi",
            data = uiState.data.transmission
          )
          Specification(
            title = "Kilometer",
            data = "${uiState.data.mileage} km"
          )
          Specification(
            title = "Bahan Bakar",
            data = uiState.data.fuel
          )
          Specification(
            title = "Kapasitas Mesin",
            data = "${uiState.data.engineCapacity} cc"
          )
          Specification(
            title = "Kapasitas Penumpang",
            data = "${uiState.data.seatCapacity} kursi"
          )
        }

        Spacer(modifier = modifier.height(7.dp))

        Button(
          modifier = modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(14.dp),
          onClick = {
            navigation.navigate("Cart")
            viewModel.purchaseCar(productDetail)
          },
          colors = ButtonDefaults.buttonColors(
            backgroundColor = TaxiSoftRed
          )
        ) {
          Text(
            "AJUKAN SEKARANG",
            fontWeight = FontWeight.W700,
            color = Color.White
          )
        }
      }
    }
    is UiState.Error -> {}
  }
}

@Composable
fun Specification(
  title: String,
  data: String,
  modifier: Modifier = Modifier
) {
  Row(
    modifier = modifier
      .fillMaxWidth()
      .padding(vertical = 8.dp),
    horizontalArrangement = Arrangement.SpaceBetween
  ) {
    Text(
      title,
      color = Color(0xFF888888)
    )
    Text(
      text = data,
      color = Color(0xFF333333),
      fontWeight = FontWeight.W700
    )
  }
}

@Preview(showBackground = true)
@Composable
fun ProductDetailScreenPreview() {
  MomobilTheme {
    ProductDetailScreen(1)
  }
}