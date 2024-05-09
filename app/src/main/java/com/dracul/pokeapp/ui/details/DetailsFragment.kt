package com.dracul.pokeapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePaddingRelative
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.dracul.pokeapp.databinding.FragmentDetailsBinding
import com.dracul.pokeapp.ui.main.recycler.SpriteAdapter
import com.dracul.pokeapp.utills.poop
import com.dracul.pokeapp.viewmodels.DetailsViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val vm by viewModel<DetailsViewModel>()
    private val adapter = SpriteAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = DetailsFragmentArgs.fromBundle(requireArguments()).id
        vm.setId(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)
        lifecycleScope.launch {
            vm.pokemonData.collect{
                adapter.submitList(it.sprites.toStringList())
            }
        }
        binding.run {
            rvSprites.adapter = adapter
            poop(adapter.currentList.size)
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
                root.updatePaddingRelative(bottom = systemBars.bottom)
                insets
            }
            lifecycleScope.launch {
                vm.pokemonData.collect{
                    tvIdValue.text=it.id.toString()
                    tvNameValue.text=it.name
                    tvHeightValue.text=it.height.toString()
                    tvWeightValue.text=it.weight.toString()
                    tvExperienceValue.text = it.baseExperience.toString()
                    Glide
                        .with(root)
                        .load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png")
                        .diskCacheStrategy(DiskCacheStrategy.ALL)
                        .into(ivPokemon)
                    adapter.submitList(it.sprites.toStringList())

                }
            }

        }


        return binding.root
    }
}