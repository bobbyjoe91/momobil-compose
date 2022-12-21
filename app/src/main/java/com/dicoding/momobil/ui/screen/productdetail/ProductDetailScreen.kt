package com.dicoding.momobil.ui.screen.productdetail

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Share
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.dicoding.momobil.R
import com.dicoding.momobil.di.Injector
import com.dicoding.momobil.ui.ViewModelFactory
import com.dicoding.momobil.ui.common.Helpers
import com.dicoding.momobil.ui.common.UiState
import com.dicoding.momobil.ui.theme.*
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState

@OptIn(ExperimentalPagerApi::class)
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
  val pagerState = rememberPagerState()
  val context = LocalContext.current

  when(
    val uiState = viewModel.uiState.collectAsState(initial = UiState.Loading).value
  ) {
    is UiState.Loading -> {
      viewModel.getProductById(productId)
    }
    is UiState.Success -> {
      val productDetail = uiState.data
      val imagesSize = productDetail.images.size
      val shareMessage = stringResource(
        id = R.string.intent_message,
        productDetail.name,
        productDetail.year,
        productDetail.transmission.lowercase(),
        productDetail.fuel.lowercase()
      )

      Column(
        modifier = modifier
          .fillMaxSize()
          .background(color = TaxiWhite)
          .verticalScroll(rememberScrollState()),
      ) {
        Box {
          HorizontalPager(
            count = imagesSize,
            state = pagerState
          ) { index ->
            AsyncImage(
              model = productDetail.images[index],
              contentDescription = "Product image $index",
              contentScale = ContentScale.Crop,
              modifier = modifier
                .fillMaxWidth()
                .height(290.dp)
            )
          }
          Text(
            "${pagerState.currentPage + 1} / $imagesSize",
            modifier = modifier
              .background(Color.LightGray)
              .padding(5.dp)
              .clip(RoundedCornerShape(5.dp))
              .align(Alignment.BottomEnd)
          )
        }
        Column(
          modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 24.dp, vertical = 20.dp)
        ) {
          Text(
            text = productDetail.name,
            fontSize = 20.sp,
            fontWeight = FontWeight.W700
          )
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            "Nominal Harga",
            color = TaxiLightGray
          )
          Spacer(modifier = Modifier.height(5.dp))
          Text(
            text = Helpers.toCurrency(productDetail.price),
            color = TaxiDarkBlue,
            fontSize = 18.sp,
            fontWeight = FontWeight.W700
          )
        }
        
        Spacer(modifier = modifier.height(7.dp))

        Row(
          verticalAlignment = Alignment.CenterVertically,
          horizontalArrangement = Arrangement.SpaceBetween,
          modifier = modifier
            .fillMaxWidth()
            .background(color = Color.White)
            .padding(horizontal = 24.dp, vertical = 14.dp)
        ) {
          Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
          ) {
            Icon(
              imageVector = Icons.Default.AccountCircle,
              contentDescription = "Seller profile",
              modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(14.dp))

            Column {
              Text(
                text = productDetail.sellerName,
                fontWeight = FontWeight.W700,
                fontSize = 16.sp
              )
              Text(
                text = productDetail.location,
                color = TaxiLightGray,
                fontSize = 14.sp
              )
            }
          }

          IconButton(
            onClick = {
              shareProduct(context, productDetail.name, shareMessage)
            }
          ) {
            Icon(
              imageVector = Icons.Default.Share,
              contentDescription = "Share button",
              modifier = Modifier.size(24.dp)
            )
          }
        }

        Spacer(modifier = modifier.height(7.dp))

        Text(
          stringResource(R.string.spesifikasi_mobil),
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
            title = stringResource(id = R.string.warna),
            data = productDetail.color
          )
          Specification(
            title = stringResource(R.string.tahun_produksi),
            data = productDetail.year.toString()
          )
          Specification(
            title = stringResource(id = R.string.transmisi),
            data = productDetail.transmission
          )
          Specification(
            title = stringResource(id = R.string.kilometer),
            data = "${productDetail.mileage} km"
          )
          Specification(
            title = stringResource(id = R.string.bahan_bakar),
            data = productDetail.fuel
          )
          Specification(
            title = stringResource(id = R.string.kapasitas_mesin),
            data = "${productDetail.engineCapacity} cc"
          )
          Specification(
            title = stringResource(id = R.string.kapasitas_penumpang),
            data = "${productDetail.seatCapacity} kursi"
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
            stringResource(id = R.string.beli_sekarang),
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
      color = TaxiDarkGray
    )
    Text(
      text = data,
      color = TaxiDarkerGray,
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

private fun shareProduct(context: Context, name: String, message: String) {
  val subjectString = context.resources.getString(R.string.subject, name)
  val intent = Intent(Intent.ACTION_SEND).apply {
    type = "text/plain"
    putExtra(Intent.EXTRA_SUBJECT, subjectString)
    putExtra(Intent.EXTRA_TEXT, message)
  }

  context.startActivity(
    Intent.createChooser(intent, subjectString)
  )
}