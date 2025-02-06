package com.example.ejemplo_login.viewmodel

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.ejemplo_login.api.RetrofitConnect
import com.example.ejemplo_login.models.Post
import kotlinx.coroutines.launch


/** Con el viewmodel eviamos las fugas de memoria. Aquí accedemos a los datos
 * y los almacenamos en una lista mutable*/
class PostViewModel : ViewModel() {

    // Almacena la lista de posts y notifica cambios al Fragment.
    private val _posts = MutableLiveData<List<Post>>()
    val posts: LiveData<List<Post>> get() = _posts

    private val _post = MutableLiveData<Post>()
    val post: LiveData<Post> get() = _post

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    /** Método que llama a la aPI mediante una corrutina para acualizar _posts*/
    fun fetchPost(){
        Log.d("App","FETCH");
        //Ejecuta en un hilo secundario
        viewModelScope.launch {
            try{
                val response = RetrofitConnect.api.getPosts()
                print(response.listIterator(0))

                _posts.value = response
            } catch (e: Exception){
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }


    // Obtener un solo post por ID
    fun fetchPostById(id: Int) {
        viewModelScope.launch {
            try {
                _post.value = RetrofitConnect.api.getPostById(id)
            } catch (e: Exception) {
                _message.value = "Error al obtener post: ${e.message}"
            }
        }
    }

    // Crear un nuevo post
    fun createPost(post: Post) {
        viewModelScope.launch {
            try {
                val newPost = RetrofitConnect.api.createPost(post)
                _message.value = "Post creado con ID: ${newPost.id}"
            } catch (e: Exception) {
                _message.value = "Error al crear post: ${e.message}"
            }
        }
    }

    // Actualizar un post
    fun updatePost(id: Int, post: Post) {
        viewModelScope.launch {
            try {
                val updatedPost = RetrofitConnect.api.updatePost(id, post)
                _message.value = "Post actualizado: ${updatedPost.title}"
            } catch (e: Exception) {
                _message.value = "Error al actualizar post: ${e.message}"
            }
        }
    }

    // Modificar solo un campo del post
    fun patchPost(id: Int, field: Map<String, String>) {
        viewModelScope.launch {
            try {
                val updatedPost = RetrofitConnect.api.patchPost(id, field)
                _message.value = "Post modificado: ${updatedPost.title}"
            } catch (e: Exception) {
                _message.value = "Error al modificar post: ${e.message}"
            }
        }
    }

    // Eliminar un post
    fun deletePost(id: Int) {
        viewModelScope.launch {
            try {
                val response = RetrofitConnect.api.deletePost(id)
                if (response.isSuccessful) {
                    _message.value = "Post eliminado correctamente"
                } else {
                    _message.value = "Error al eliminar post"
                }
            } catch (e: Exception) {
                _message.value = "Error al eliminar post: ${e.message}"
            }
        }
    }

}