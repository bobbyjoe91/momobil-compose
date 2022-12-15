package com.dicoding.momobil.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.momobil.repository.CartRepository
import com.dicoding.momobil.repository.ProductRepository
import com.dicoding.momobil.ui.screen.cart.CartViewModel
import com.dicoding.momobil.ui.screen.landingpage.LandingPageViewModel
import com.dicoding.momobil.ui.screen.productdetail.ProductDetailViewModel

class ViewModelFactory(
  private val productRepo: ProductRepository? = null,
  private val cartRepo: CartRepository? = null
) : ViewModelProvider.NewInstanceFactory() {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(LandingPageViewModel::class.java)) {
      return productRepo?.let { LandingPageViewModel(productRepo) } as T
    } else if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
      if (productRepo != null && cartRepo != null) {
        return ProductDetailViewModel(productRepo, cartRepo) as T
      }
    } else if (modelClass.isAssignableFrom(CartViewModel::class.java)) {
      return cartRepo?.let { CartViewModel(cartRepo) } as T
    }

    throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
  }
}