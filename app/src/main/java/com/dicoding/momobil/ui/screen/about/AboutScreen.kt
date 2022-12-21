package com.dicoding.momobil.ui.screen.about

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dicoding.momobil.R
import com.dicoding.momobil.ui.theme.MomobilTheme

@Composable
fun AboutScreen(
  modifier: Modifier = Modifier,
) {
  Column(
    modifier = modifier.fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Image(
      painter = painterResource(id = R.drawable.about_photo),
      contentDescription = "profile picture",
      contentScale = ContentScale.Crop,
      modifier = modifier
        .size(150.dp)
        .clip(shape = CircleShape)
        .shadow(
          elevation = 10.dp,
          shape = CircleShape,
          clip = true
        )
    )

    Spacer(modifier = modifier.height(10.dp))

    Text(
      stringResource(id = R.string.dev_name),
      fontSize = 20.sp,
      fontWeight = FontWeight.W700
    )
    Spacer(modifier = modifier.height(30.dp))

    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Icon(
        imageVector = Icons.Default.Email,
        contentDescription = "Email icon",
        modifier = modifier.padding(end = 5.dp)
      )
      Text(
        stringResource(id = R.string.dev_email),
        textDecoration = TextDecoration.Underline
      )
    }
    Row(
      verticalAlignment = Alignment.CenterVertically
    ) {
      Image(
        painter = painterResource(R.drawable.github),
        contentDescription = "Github icon",
        modifier = Modifier
          .size(28.dp)
          .padding(end = 5.dp)
      )
      Text(stringResource(id = R.string.dev_github))
    }

    Spacer(modifier = modifier.height(50.dp))
    Text(stringResource(id = R.string.copyright))
  }
}

@Preview(showBackground = true)
@Composable
fun AboutScreenPreview() {
  MomobilTheme {
    AboutScreen()
  }
}