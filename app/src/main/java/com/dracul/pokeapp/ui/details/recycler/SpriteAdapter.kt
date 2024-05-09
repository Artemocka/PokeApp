package com.dracul.pokeapp.ui.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dracul.pokeapp.databinding.ItemSpriteBinding
import com.dracul.pokeapp.ui.details.recycler.SpriteItemCallBack

class SpriteAdapter : ListAdapter<String, SpriteAdapter.ViewHolder>(SpriteItemCallBack()) {


    override fun getItemId(position: Int): Long {
        return currentList[position].hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemSpriteBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = ViewHolder(binding)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item)
    }

    class ViewHolder(
        private val binding: ItemSpriteBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: String) {
            binding.run {
                Glide
                    .with(binding.root)
                    .load(item)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivSprite)
            }
        }
    }
}
