package com.gk.udemynewsapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.gk.udemynewsapp.R
import com.gk.udemynewsapp.databinding.ActivityMainBinding
import com.gk.udemynewsapp.domain.usecase.GetNewsHeadlineUseCase
import com.gk.udemynewsapp.presentation.viewmodel.NewsViewModel
import com.gk.udemynewsapp.presentation.viewmodel.NewsViewModelFactory
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

//    @Inject
//    lateinit var getNewsHeadlineUseCase: GetNewsHeadlineUseCase
//    lateinit var viewModel: NewsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController
        binding.bottomNavigationView.setupWithNavController(
            navController
        )

//        val factory = NewsViewModelFactory(getNewsHeadlineUseCase = getNewsHeadlineUseCase)
//        viewModel = ViewModelProvider(this, factory)[NewsViewModel::class.java]
    }
}