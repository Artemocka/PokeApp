package com.dracul.pokeapp.ui.main.recycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dracul.pokeapp.databinding.ItemPokemonBinding
import com.example.domain.models.Result

class ResultAdapter(
) : ListAdapter<Result, ResultAdapter.ViewHolder>(ResultItemCallBack()) {


    override fun getItemId(position: Int): Long {
        return currentList[position].name.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemPokemonBinding.inflate(
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
        private val binding: ItemPokemonBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var pokemonName: String

        fun bind(item: Result) {
            pokemonName = item.name
            binding.run {
                tvPokemonName.text = item.name.replaceFirstChar {
                    it.uppercaseChar()
                }
                Glide.with(binding.root).load("https://img.pokemondb.net/artwork/${item.name}.jpg")
                    .diskCacheStrategy(DiskCacheStrategy.ALL).into(ivPokemon)

            }
        }
    }
}


