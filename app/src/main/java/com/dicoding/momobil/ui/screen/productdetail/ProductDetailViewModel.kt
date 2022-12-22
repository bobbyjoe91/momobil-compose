package com.dicoding.momobil.ui.screen.productdetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.momobil.model.Mobil
import com.dicoding.momobil.repository.CartRepository
import com.dicoding.momobil.repository.ProductRepository
import com.dicoding.momobil.ui.common.UiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ProductDetailViewModel(
  private val productRepo: ProductRepository,
  private val cartRepo: CartRepository
): ViewModel() {
  private val _uiState: MutableStateFlow<UiState<Mobil>> = MutableStateFlow(UiState.Loading)
  val uiState: StateFlow<UiState<Mobil>>
    get() = _uiState

  fun getProductById(mobilId: Int) {
    viewModelScope.launch {
      delay(2000L)
      _uiState.value = UiState.Success(productRepo.getMobilById(mobilId))
    }
  }

  fun purchaseCar(car: Mobil) {
    viewModelScope.launch {
      cartRepo.insertCarToCart(car)
    }
  }
}