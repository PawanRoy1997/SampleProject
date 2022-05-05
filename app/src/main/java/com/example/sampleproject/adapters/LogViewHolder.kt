package com.example.sampleproject.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.databinding.LogItemBinding
import com.example.sampleproject.db.Log

class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding = LogItemBinding.bind(itemView)

    fun bind(data: Log) {
        binding.logMessage.text = data.message
        binding.logTimeStamp.text = data.timestamp.toString()
        binding.logCount.text = data.countValue.toString()
    }
}