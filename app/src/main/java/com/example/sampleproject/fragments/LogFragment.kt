package com.example.sampleproject.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.sampleproject.adapters.LogAdapter
import com.example.sampleproject.databinding.FragmentLogBinding
import com.example.sampleproject.db.Log
import com.example.sampleproject.viewModels.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class LogFragment : Fragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var logAdapter = LogAdapter()
    private lateinit var binding: FragmentLogBinding
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLogBinding.inflate(inflater, container, false)
        initView()
        return binding.root
    }

    private fun initView() {
        viewModel = ViewModelProvider(this, viewModelFactory)[MainViewModel::class.java]
        viewModel.logsLD.observe(viewLifecycleOwner, getLogObserver())
        binding.logRecyclerView.apply {
            adapter = logAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
        viewModel.getAllLogs()
    }

    private fun getLogObserver(): Observer<List<Log>> {
        return Observer { logs ->
            logAdapter.logs = logs
            logAdapter.notifyDataSetChanged()
        }
    }
}