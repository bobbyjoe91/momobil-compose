package com.dicoding.momobil.repository

import com.dicoding.momobil.model.Mobil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CartRepository {
  private var cartItems = mutableSetOf<Mobil>()

  fun getAllCartItems(): Flow<MutableSet<Mobil>> {
    return flowOf(cartItems)
  }

  fun insertCarToCart(mobil: Mobil) {
    cartItems.add(mobil)
  }

  fun removeFromCart(item: Mobil): Flow<Boolean> {
    cartItems.remove(item)
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