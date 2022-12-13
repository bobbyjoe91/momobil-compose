package com.dicoding.momobil.repository

import com.dicoding.momobil.model.Mobil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CartRepository {
  private val cartItems = mutableListOf<Mobil>()

  fun getAllCartItems(): Flow<List<Mobil>> {
    return flowOf(cartItems)
  }

  fun insertCarToCart(mobil: Mobil) {
    cartItems.add(mobil)
  }

  companion object {
    @Volatile
    private var instance: CartRepository? = null

    fun getInstance(): CartRepository = instance ?: synchronized(this) {
      CartRepository().apply { instance = this }
    }
  }
}