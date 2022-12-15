package com.dicoding.momobil.repository

import com.dicoding.momobil.model.Mobil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CartRepository {
  private var cartItems = mutableListOf<Mobil>()

  fun getAllCartItems(): Flow<MutableList<Mobil>> {
    return flowOf(cartItems)
  }

  fun insertCarToCart(mobil: Mobil) {
    cartItems.add(mobil)
  }

  fun removeFromCart(itemIndex: Int): Flow<Boolean> {
    cartItems.removeAt(itemIndex)
    return flowOf(true)
  }

  companion object {
    @Volatile
    private var instance: CartRepository? = null

    fun getInstance(): CartRepository = instance ?: synchronized(this) {
      CartRepository().apply { instance = this }
    }
  }
}