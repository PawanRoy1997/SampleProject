package com.example.sampleproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.sampleproject.databinding.FragmentClickCounterBinding
import com.example.sampleproject.navigators.AppNavigator
import com.example.sampleproject.navigators.Screens
import com.example.sampleproject.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class ClickCounterFragment : Fragment() {

    @Inject
    lateinit var navigatorImpl: AppNavigator

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var viewModel: MainViewModel
    private lateinit var binding: FragmentClickCounterBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentClickCounterBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.lastCountLD.observe(viewLifecycleOwner) { counting ->
            binding.counter.text = counting.toString()
        }
        viewModel.getLastCount()
        binding.deleteLogs.setOnClickListener(getDeleteBtnListener())
        binding.incrementCounter.setOnClickListener(getIncrementBtnListener())
        binding.seeLogs.setOnClickListener(getSeeLogsBtnListener())
        binding.resetCounter.setOnClickListener(getResetBtnListener())
    }

    private fun getIncrementBtnListener(): View.OnClickListener {
        return View.OnClickListener {
            viewModel.addNewLog("Incremented", System.currentTimeMillis(), viewModel.lastCountLD.value!! + 1)
        }
    }

    private fun getDeleteBtnListener(): View.OnClickListener {
        return View.OnClickListener {
            viewModel.deleteAllLogs()
        }
    }

    private fun getResetBtnListener(): View.OnClickListener {
        return View.OnClickListener {
            viewModel.addNewLog("Reset", System.currentTimeMillis(), 0)
        }
    }

    private fun getSeeLogsBtnListener(): View.OnClickListener {
        return View.OnClickListener {
            navigatorImpl.navigateTo(Screens.LOGS)
        }
    }
}