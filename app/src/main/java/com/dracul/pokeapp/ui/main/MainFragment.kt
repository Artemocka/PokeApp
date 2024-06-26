package com.dracul.pokeapp.ui.main

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
import com.dracul.pokeapp.databinding.FragmentMainBinding
import com.dracul.pokeapp.ui.State
import com.dracul.pokeapp.ui.main.recycler.OnItemListener
import com.dracul.pokeapp.ui.main.recycler.ResultAdapter
import com.dracul.pokeapp.viewmodels.MainViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainFragment : Fragment(), OnItemListener {

    private lateinit var binding: FragmentMainBinding
    private val vm by viewModel<MainViewModel>()
    private val adapter = ResultAdapter(this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            vm.pokemonList.collectLatest {
                adapter.submitList(it)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        binding.rvList.adapter = adapter
        binding.run {
            ViewCompat.setOnApplyWindowInsetsListener(root) { v, insets ->
                val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
                v.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
                binding.rvList.updatePaddingRelative(bottom = systemBars.bottom)
                insets
            }

            viewLifecycleOwner.lifecycleScope.launch {
                vm.state.collect {
                    when (it) {
                        State.Loading -> {
                            tvErrorText.isVisible = false
                            swipeToRefresh.isRefreshing = true
                        }

                        State.Error -> {
                            swipeToRefresh.isRefreshing = false
                            if (vm.pokemonList.value.isEmpty())
                                tvErrorText.isVisible = true
                        }

                        State.Loaded -> {
                            swipeToRefresh.isRefreshing = false
                        }
                    }
                }
            }
            viewLifecycleOwner.lifecycleScope.launch {
                vm.error.collect {
                    Snackbar.make(root, it, Snackbar.LENGTH_LONG).show()
                    tvErrorText.text = it
                }
            }
            swipeToRefresh.setOnRefreshListener {
                vm.reloadPage()
            }

        }
        return binding.root
    }

    override fun onEnd() {
        vm.nextPage()
    }

    override fun onItemClick(id: Int) {
        vm.navigateToDetails(findNavController(), id)
    }
}