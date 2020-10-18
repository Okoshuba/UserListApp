package com.example.testtask.Repository

import kotlinx.serialization.Serializable

@Serializable
data class Users (
    val userId: Int,
    val name: String,
    val email: String,
    val phone: String,
    val website: String
)

