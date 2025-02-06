package com.example.ejemplo_login

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels

import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.ejemplo_login.viewmodel.PostViewModel

class MainActivity : AppCompatActivity() {

    private val postViewModel: PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        postViewModel.fetchPost();
       /* var listado=findViewById<TextView>(R.id.lista);



        postViewModel.posts.observe(this, Observer{
            posts -> if(posts!=null){
                val texto= posts.joinToString("\n") { "Título: ${it.title}" }
                listado.text = texto
            }

        })

        postViewModel.errorMessage.observe(this, Observer { error ->
            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
        })*/

    }
}