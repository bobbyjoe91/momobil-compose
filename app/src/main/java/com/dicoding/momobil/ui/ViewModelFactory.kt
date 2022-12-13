package com.dicoding.momobil.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.dicoding.momobil.repository.CartRepository
import com.dicoding.momobil.repository.ProductRepository
import com.dicoding.momobil.ui.screen.landingpage.LandingPageViewModel
import com.dicoding.momobil.ui.screen.productdetail.ProductDetailViewModel

class ViewModelFactory(
  private val productRepo: ProductRepository,
  private val cartRepo: CartRepository? = null
) : ViewModelProvider.NewInstanceFactory() {
  @Suppress("UNCHECKED_CAST")
  override fun <T : ViewModel> create(modelClass: Class<T>): T {
    if (modelClass.isAssignableFrom(LandingPageViewModel::class.java)) {
      return LandingPageViewModel(productRepo) as T
    } else if (modelClass.isAssignableFrom(ProductDetailViewModel::class.java)) {
      return cartRepo?.let { ProductDetailViewModel(productRepo, it) } as T
    }

    throw IllegalArgumentException("Unknown ViewModel class: " + modelClass.name)
  }
}