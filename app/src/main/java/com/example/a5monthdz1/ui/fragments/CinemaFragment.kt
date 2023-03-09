package com.example.a5monthdz1.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isInvisible
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import com.example.a5monthdz1.model.CinemaModel
import com.example.a5monthdz1.ui.adapter.CinemaAdapter
import com.example.a5monthdz1.databinding.FragmentCinemaBinding

class CinemaFragment : Fragment() {

    private lateinit var binding: FragmentCinemaBinding
    private val list = mutableListOf<CinemaModel>()
    private var viewModel: CinemaViewModel? = null
    private var cinemaAdapter = CinemaAdapter(this::onItemClick)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCinemaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[CinemaViewModel::class.java]
        setupObserves()
        setupListener()
        clear()
        initialize()
    }

    private fun setupObserves() {
        viewModel?.getListOfCinemaHTTP()?.observe(viewLifecycleOwner) {
            cinemaAdapter.setList(it)
        }
    }

    private fun setupListener() {
        binding.btnOpen.setOnClickListener {
            binding.btnOpen.isInvisible = true
            binding.rvCinema.isInvisible = false
        }
    }

    private fun clear() {
        list.clear()
    }

    private fun initialize() {
        binding.rvCinema.adapter = cinemaAdapter
    }

    private fun onItemClick(cinemaModel: CinemaModel) {
        val action: NavDirections = CinemaFragmentDirections.actionCinemaFragmentToDetailFragment(
            cinemaModel.image,
            cinemaModel.name
        )
        findNavController().navigate(action)
    }
}