package com.dicoding.momobil.ui.screen.landingpage

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.momobil.model.Mobil
import com.dicoding.momobil.repository.ProductRepository
import com.dicoding.momobil.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class LandingPageViewModel(
  private val productRepo: ProductRepository
): ViewModel() {
  private val _uiState: MutableStateFlow<UiState<List<Mobil>>> = MutableStateFlow(UiState.Loading)
  val uiState: StateFlow<UiState<List<Mobil>>>
    get() = _uiState

  fun getAllProducts() {
    viewModelScope.launch {
      productRepo.showAllProduct()
        .catch {
          _uiState.value = UiState.Error(it.message.toString())
        }
        .collect { productList ->
          _uiState.value = UiState.Success(productList)
        }
    }
  }
}