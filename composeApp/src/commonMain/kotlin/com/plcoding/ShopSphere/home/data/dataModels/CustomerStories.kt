package com.plcoding.ShopSphere.home.data.dataModels

data class CustomerStories(
    val customerId: Long? = null,
    val name: String = "",
    val rating: Double = 0.0,
    val review: String = "",
    val dpUrl: String = ""
)
