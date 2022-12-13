package com.dicoding.momobil.repository

import com.dicoding.momobil.data.FakeProductList
import com.dicoding.momobil.model.Mobil
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class ProductRepository {
  private val productList = mutableListOf<Mobil>()

  init {
    if (productList.isEmpty()) {
      FakeProductList.mobilList.forEach { mobil ->
        productList.add(mobil)
      }
    }
  }

  fun showAllProduct(): Flow<List<Mobil>> {
    return flowOf(productList)
  }

  fun getMobilById(id: Int): Mobil {
    return productList[id - 1]
  }

  companion object {
    @Volatile
    private var instance: ProductRepository? = null

    fun getInstance(): ProductRepository = instance ?: synchronized(this) {
      ProductRepository().apply { instance = this }
    }
  }
}