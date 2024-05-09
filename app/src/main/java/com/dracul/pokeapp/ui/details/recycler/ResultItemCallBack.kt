package com.dracul.pokeapp.ui.details.recycler

import androidx.recyclerview.widget.DiffUtil




class SpriteItemCallBack : DiffUtil.ItemCallback<String>() {
    override fun areItemsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: String, newItem: String): Boolean {
        return oldItem == newItem
    }

}