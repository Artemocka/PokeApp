package com.dracul.pokeapp.ui.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isVisible
import androidx.core.view.updatePaddingRelative
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.dracul.pokeapp.R
import com.dracul.pokeapp.databinding.FragmentDetailsBinding
import com.dracul.pokeapp.viewmodels.DetailsViewModel
import com.example.domain.models.pokemondata.Stats
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DetailsFragment : Fragment() {
    private lateinit var binding: FragmentDetailsBinding
    private val vm by viewModel<DetailsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val id = DetailsFragmentArgs.fromBundle(requireArguments()).id
        vm.setId(id)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailsBinding.inflate(layoutInflater)

        binding.run {
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
                root.updatePaddingRelative(bottom = systemBars.bottom)
                insets
            }

            viewLifecycleOwner.lifecycleScope.launch {
                vm.error.collect {
                    Snackbar.make(root, it, Snackbar.LENGTH_LONG).show()
                    tvError.text = it
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                vm.state.collect {
                    when (it) {
                        State.Loading -> piLoading.isVisible = true
                        State.Error -> {
                            piLoading.isVisible = false
                            showErrorUi()
                        }

                        State.Loaded -> {
                            hideErrorUi()
                            showLoadedUi()
                        }
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                vm.pokemonData.collect {
                    it?.let {
                        Glide.with(root).load("https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/${it.id}.png").into(ivPokemon)
                        toolbar.title = it.name.replaceFirstChar { firstChar ->
                            firstChar.uppercaseChar()
                        }
                        tvWeight.text = getString(R.string.kg).format(it.weight / 100f)
                        tvHeight.text = getString(R.string.m).format(it.height / 100f)
                        pbExpirience.setProgress(it.baseExperience, true)
                        binding.setStats(it.stats)
                    }
                }
            }
            btnReload.setOnClickListener {
                vm.reload()
            }
            toolbar.setNavigationOnClickListener {
                vm.navigateBack(findNavController())
            }
        }
        return binding.root
    }

    private fun FragmentDetailsBinding.setStats(stat: List<Stats>) {
        stat.forEach { stats ->
            when (stats.stat.name) {
                "hp" -> pbHp.setProgress(stats.baseStat, true)
                "attack" -> pbAttack.setProgress(stats.baseStat, true)
                "defense" -> pbDefense.setProgress(stats.baseStat, true)
                "speed" -> pbSpeed.setProgress(stats.baseStat, true)
                "special-attack" -> pbSpecialAttack.setProgress(stats.baseStat, true)
                "special-defense" -> pbSpecialDefense.setProgress(stats.baseStat, true)
            }
        }
    }

    private fun FragmentDetailsBinding.showErrorUi() {
        ivNoInternet.isVisible = true
        tvError.isVisible = true
        btnReload.isVisible = true
    }

    private fun FragmentDetailsBinding.hideErrorUi() {
        ivNoInternet.isVisible = false
        tvError.isVisible = false
        btnReload.isVisible = false
    }

    fun FragmentDetailsBinding.showLoadedUi() {
        cvTop.isVisible = true
        cvBottom.isVisible = true
    }
}