package com.udacity.asteroidradar.ui.main

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.udacity.asteroidradar.R
import com.udacity.asteroidradar.databinding.FragmentMainBinding
import com.udacity.asteroidradar.repository.AsteroidFilter
import com.udacity.asteroidradar.utils.ViewModelFactory

class MainFragment : Fragment() {

    private val viewModel by viewModels<MainViewModel> { ViewModelFactory(requireActivity().application) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentMainBinding.inflate(inflater)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel
        binding.asteroidRecycler.adapter = AsteroidAdapter(AsteroidListener {
            findNavController().navigate(MainFragmentDirections.actionShowDetail(it))
        })
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.main_overflow_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.show_all_menu -> AsteroidFilter.WEEK
                R.id.show_rent_menu -> AsteroidFilter.TODAY
                else -> AsteroidFilter.SAVED
            }
        )
        return true
    }
}
