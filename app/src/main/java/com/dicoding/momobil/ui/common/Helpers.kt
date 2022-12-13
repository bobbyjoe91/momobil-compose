package com.dicoding.momobil.ui.common

import java.text.DecimalFormat
import java.text.NumberFormat

class Helpers {
  companion object {
    fun toCurrency(price: Int): String {
      val tripleDigit: DecimalFormat = NumberFormat.getInstance() as DecimalFormat
      tripleDigit.applyPattern("#,###")

      val formattedPrice: String = tripleDigit.format(price).replace(',', '.')
      return "Rp$formattedPrice"
    }
  }
}