package com.dicoding.momobil.model

data class Mobil(
  val id: Int,
  val name: String,
  val description: String?,
  val price: Int,
  val images: List<String>,
  val fuel: String,
  val year: Int,
  val color: String,
  val mileage: Int,
  val location: String,
  val transmission: String,
  val seatCapacity: Int,
  val engineCapacity: Int,
  val sellerName: String,
)