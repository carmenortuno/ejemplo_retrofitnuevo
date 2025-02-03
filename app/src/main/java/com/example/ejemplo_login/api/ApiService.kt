package com.example.ejemplo_login.api

import com.example.ejemplo_login.models.Post
import retrofit2.http.GET

/*La interfaz ApiService define la estructura de comunicación con la API.
 Retrofit genera una implementación de esta interfaz automáticamente en tiempo de ejecución.
 */

interface ApiService {
    @GET("posts")    //solicitud HTTP GET al endpoint posts
    suspend fun getPosts(): List<Post>
   // suspend maneja la llamada en una corrutina: Una función suspend es una función de suspensión que puede pausarse y reanudarse sin bloquear el hilo en el que se ejecuta.
}
