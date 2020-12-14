package com.example.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.project.databinding.RestaurantShowerFragmentBinding
import androidx.lifecycle.ViewModelProviders.of

class RestaurantShowerFragment : Fragment() {


    private val viewModel: RestaurantShowerViewModel by lazy {
        ViewModelProvider(this).get(RestaurantShowerViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding = RestaurantShowerFragmentBinding.inflate(inflater)

        binding.setLifecycleOwner(this)

        binding.viewModel = viewModel
        setHasOptionsMenu(true)

        return binding.root

    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = of(this).get(RestaurantShowerViewModel::class.java)

    }

}