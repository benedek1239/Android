package com.example.project.detail

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.project.databinding.RestaurantDetailBinding

class DetailFragment : Fragment() {
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val application = requireNotNull(activity).application
        val binding = RestaurantDetailBinding .inflate(inflater)
        binding.lifecycleOwner = this
        val restaurantProperty = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty
        val viewModelFactory = DetailViewModelFactory(restaurantProperty, application)
        binding.viewModel = ViewModelProvider(
                this, viewModelFactory).get(DetailViewModel::class.java)
        return binding.root
    }
}