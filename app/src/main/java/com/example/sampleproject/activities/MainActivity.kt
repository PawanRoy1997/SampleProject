package com.example.sampleproject.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject.databinding.ActivityMainBinding
import com.example.sampleproject.navigators.AppNavigator
import com.example.sampleproject.navigators.Screens
import com.example.sampleproject.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var navigator: AppNavigator
    @Inject
    lateinit var viewModelProvider: ViewModelProvider.Factory

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        viewModel = ViewModelProvider(this, viewModelProvider)[MainViewModel::class.java]
        setContentView(binding.root)
        if (savedInstanceState == null) navigator.navigateTo(Screens.CLICK_COUNTER)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.backStackEntryCount == 0) {
            finish()
        }
    }
}