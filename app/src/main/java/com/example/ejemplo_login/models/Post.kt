package com.example.ejemplo_login.models

//Modelo para trabajar con la API: https://jsonplaceholder.typicode.com/posts
data class Post(
    val userId: Int,
    val id: Int,
    val title: String,
    val body: String
)