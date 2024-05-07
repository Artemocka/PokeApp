package com.dracul.pokeapp.ui.main.recycler

import androidx.recyclerview.widget.DiffUtil
import com.dracul.pokeapp.domain.models.Result

class ResultItemCallBack : DiffUtil.ItemCallback<Result>() {
    override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
        return oldItem == newItem
    }

}