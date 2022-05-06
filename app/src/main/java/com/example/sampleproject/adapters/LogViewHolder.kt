package com.example.sampleproject.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.sampleproject.databinding.LogItemBinding
import com.example.sampleproject.db.Log
import java.text.SimpleDateFormat
import java.util.*

class LogViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding = LogItemBinding.bind(itemView)
    private val sdf = SimpleDateFormat("dd MM yyyy HH:mm", Locale.ENGLISH).apply {
        timeZone = TimeZone.getTimeZone("Asia/Kolkata")
    }

    fun bind(data: Log) {
        binding.logMessage.text = data.message
        binding.logTimeStamp.text = sdf.format(data.timestamp)
        binding.logCount.text = data.countValue.toString()
    }
}