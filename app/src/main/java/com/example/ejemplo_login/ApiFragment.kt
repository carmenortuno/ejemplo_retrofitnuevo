package com.example.ejemplo_login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ejemplo_login.adapters.PostAdapter
import com.example.ejemplo_login.viewmodel.PostViewModel

class ApiFragment : Fragment() {

    private val postViewModel: PostViewModel by activityViewModels() // ViewModel compartido con MainActivity
    private lateinit var postAdapter: PostAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_api, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView: RecyclerView = view.findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        postAdapter = PostAdapter()

        recyclerView.adapter = postAdapter //  Asigna el adapter

        postViewModel.posts.observe(viewLifecycleOwner) { posts ->
            if (!posts.isNullOrEmpty()) {
                postAdapter.submitList(posts) // Actualiza la lista del adapter
            } else {
                Toast.makeText(requireContext(), "No hay datos", Toast.LENGTH_SHORT).show()
            }
        }

        postViewModel.errorMessage.observe(viewLifecycleOwner) { message ->
            Toast.makeText(requireContext(), message, Toast.LENGTH_LONG).show()
        }
    }
}
