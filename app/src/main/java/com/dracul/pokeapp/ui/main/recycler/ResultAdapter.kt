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
    private val listener: OnItemListener
) : ListAdapter<Result, ResultAdapter.ViewHolder>(ResultItemCallBack()) {


    override fun getItemId(position: Int): Long {
        return currentList[position].name.hashCode().toLong()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = ItemPokemonBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        val viewHolder = ViewHolder(binding, listener)
        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = currentList[position]
        holder.bind(item, position+1)
        if (currentList.isNotEmpty()){
            if (position == itemCount.dec()) {
                listener.onEnd()
            }
        }
    }

    class ViewHolder(
        private val binding: ItemPokemonBinding,
        private val listener: OnItemListener,
    ) : RecyclerView.ViewHolder(binding.root) {
        private lateinit var item: Result
        init {
            binding.root.setOnClickListener { listener.onItemClick(item.name) }
        }
        fun bind(item: Result,id: Int) {
            binding.run {
                tvPokemonName.text = item.name.replaceFirstChar {
                    it.uppercaseChar()
                }
                Glide
                    .with(binding.root)
                    .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${id}.png")
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivPokemon)

            }
        }
    }
}

interface OnItemListener {
    fun onEnd()
    fun onItemClick(pokemonName: String)
}
