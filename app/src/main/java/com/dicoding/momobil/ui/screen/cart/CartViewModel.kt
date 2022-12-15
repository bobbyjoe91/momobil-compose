package com.dicoding.momobil.ui.screen.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dicoding.momobil.model.Mobil
import com.dicoding.momobil.repository.CartRepository
import com.dicoding.momobil.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class CartViewModel(
  private val cartRepo: CartRepository
): ViewModel() {
  private val _uiState: MutableStateFlow<UiState<MutableList<Mobil>>> = MutableStateFlow(UiState.Loading)
  val uiState: StateFlow<UiState<MutableList<Mobil>>>
    get() = _uiState

  fun showAllItems() {
    viewModelScope.launch {
      _uiState.value = UiState.Loading
      cartRepo.getAllCartItems()
        .collect { cartItems ->
          val copy = mutableListOf<Mobil>()
          copy.addAll(cartItems)

          _uiState.value = UiState.Success(copy)
        }
    }
  }

  fun removeFromCart(itemIndex: Int) {
    viewModelScope.launch {
      cartRepo.removeFromCart(itemIndex)
        .catch {
          _uiState.value = UiState.Error(it.message.toString())
        }
        .collect { shouldReload ->
          if (shouldReload) showAllItems()
        }
    }
  }
}