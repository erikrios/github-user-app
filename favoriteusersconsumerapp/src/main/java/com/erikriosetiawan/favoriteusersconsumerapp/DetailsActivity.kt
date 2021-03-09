package com.erikriosetiawan.favoriteusersconsumerapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.erikriosetiawan.favoriteusersconsumerapp.databinding.ActivityDetailsBinding

class DetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}