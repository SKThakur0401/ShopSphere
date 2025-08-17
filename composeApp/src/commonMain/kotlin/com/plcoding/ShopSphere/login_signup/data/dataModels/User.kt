package com.plcoding.ShopSphere.login_signup.data.dataModels

import kotlinx.serialization.Serializable

@Serializable
data class User(
    val userId: String,
    val userName: String,
    val password: String,
    val email: String,
    val cartItemList: List<String> = emptyList()
)
