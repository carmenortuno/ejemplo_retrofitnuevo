package com.example.ejemplo_login.viewmodel

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

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    /** Método que llama a la aPI mediante una corrutina para acualizar _posts*/
    fun fetchPost(){
        //Ejecuta en un hilo secundario
        viewModelScope.launch {
            try{
                val response = RetrofitConnect.api.getPosts()
                _posts.value = response
            } catch (e: Exception){
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}