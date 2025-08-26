package com.plcoding.ShopSphere.home.data.dataModels

data class Product(
    val productId: Long? = null,
    val productName: String = "",
    val price: Double = 0.0,
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val productImgUrl: String = "",
    val tag : String = "BEST SELLER"
)


