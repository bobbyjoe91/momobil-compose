package com.dicoding.momobil.navigation

sealed class Screen(val routeName: String) {
  object LandingPage: Screen("LandingPage")
  object Cart: Screen("Cart")
  object About: Screen("About")
  object ProductDetail: Screen("ProductDetail/{id}") {
    fun getRouteWithId(id: Int) = "ProductDetail/$id"
  }
}