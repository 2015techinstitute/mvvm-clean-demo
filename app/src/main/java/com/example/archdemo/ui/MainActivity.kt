package com.example.archdemo.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.archdemo.databinding.ActivityMainBinding
import com.example.archdemo.presentation.PostViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val postsViewModel : PostViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        postsViewModel.posts.observe(this){
            binding.txtMsg.text = it.toString()
        }
    }

    override fun onResume() {
        super.onResume()
        postsViewModel.getPostsActual()
    }
}