package com.dicoding.momobil.di

import com.dicoding.momobil.repository.CartRepository
import com.dicoding.momobil.repository.ProductRepository

object Injector {
  fun injectProductRepository(): ProductRepository {
    return ProductRepository.getInstance()
  }

  fun injectCartRepository(): CartRepository {
    return CartRepository.getInstance()
  }
}