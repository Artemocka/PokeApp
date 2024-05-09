package com.dracul.pokeapp.ui.main.recycler

import androidx.recyclerview.widget.DiffUtil
import com.example.domain.models.Result

class ResultItemCallBack : DiffUtil.ItemCallback<com.example.domain.models.Result>() {
    override fun areItemsTheSame(oldItem: com.example.domain.models.Result, newItem: com.example.domain.models.Result): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: com.example.domain.models.Result, newItem: com.example.domain.models.Result): Boolean {
        return oldItem == newItem
    }

}