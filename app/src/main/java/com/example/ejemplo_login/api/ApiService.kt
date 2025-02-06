package com.example.ejemplo_login.api

import com.example.ejemplo_login.models.Post
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

/*La interfaz ApiService define la estructura de comunicación con la API.
 Retrofit genera una implementación de esta interfaz automáticamente en tiempo de ejecución.
 */

interface ApiService {
    @GET("posts")    //solicitud HTTP GET al endpoint posts
    suspend fun getPosts(): List<Post>
   // suspend maneja la llamada en una corrutina: Una función suspend es una función de suspensión que puede pausarse y reanudarse sin bloquear el hilo en el que se ejecuta.

    // Obtener un post por ID
    @GET("posts/{id}")
    suspend fun getPostById(@Path("id") id: Int): Post

    // Crear un nuevo post
    @POST("posts")
    suspend fun createPost(@Body post: Post): Post

    // Actualizar un post por completo (PUT)
    @PUT("posts/{id}")
    suspend fun updatePost(@Path("id") id: Int, @Body post: Post): Post

    // Actualizar un campo específico del post (PATCH)
    @PATCH("posts/{id}")
    suspend fun patchPost(@Path("id") id: Int, @Body post: Map<String, String>): Post

    // Eliminar un post
    @DELETE("posts/{id}")
    suspend fun deletePost(@Path("id") id: Int): Response<Unit> //Response<Unit> en DELETE porque la API devuelve un 204 No Content.
}
