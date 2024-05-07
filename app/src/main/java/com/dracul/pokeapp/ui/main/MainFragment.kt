package com.dracul.pokeapp.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.dracul.pokeapp.databinding.FragmentMainBinding
import com.dracul.pokeapp.ui.main.recycler.ResultAdapter
import com.dracul.pokeapp.viewmodels.MainViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch


class MainFragment : Fragment() {

    private lateinit var binding :FragmentMainBinding
    private val viewModel by viewModels<MainViewModel>()
    private val adapter = ResultAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.pokemonList.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.rvList.adapter = adapter

        return binding.root
    }

}