package com.example.project.detail

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.navigation.findNavController
import com.example.project.R
import com.example.project.databinding.RestaurantDetailBinding


class DetailFragment : Fragment() {
    @SuppressLint("UseRequireInsteadOfGet")
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val application = requireNotNull(activity).application

        val binding = RestaurantDetailBinding .inflate(inflater)

        binding.lifecycleOwner = this

        val restaurantProperty = DetailFragmentArgs.fromBundle(arguments!!).selectedProperty

        val viewModelFactory = DetailViewModelFactory(restaurantProperty, application)

        binding.viewModel = ViewModelProvider(this, viewModelFactory).get(DetailViewModel::class.java)

        //vissza gomb deklarálása és click listener hozzá csatolása
        val backBTN = binding.root.findViewById<ImageButton>(R.id.back_button_detail)
        backBTN.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_detailFragment_to_restaurantsFragment)
        }

        //telefonszám kiszedése a view ből
        val phoneNumber = binding.root.findViewById<TextView>(R.id.restaurant_detail_phone)

        //hívás gomb deklarálása és click listener hozzá csatolása
        val phoneBTN = binding.root.findViewById<ImageButton>(R.id.phone_button_detail)
        phoneBTN.setOnClickListener { view : View ->
            val intent = Intent(Intent.ACTION_CALL, Uri.parse("tel:" + "${phoneNumber.text}"))
            requireContext().startActivity(intent)
        }

        //koordináták kiszedése a view ből
        //val latitude = binding.root.findViewById<TextView>(R.id.restaurant_detail_lat)
       // val longitude = binding.root.findViewById<TextView>(R.id.restaurant_detail_lng)

        //térkép gomb deklarálása és click listener hozzá csatolása
        val mapBTN = binding.root.findViewById<ImageButton>(R.id.map_button_detail)
        mapBTN.setOnClickListener { view : View ->
           // Log.d("Latitude: ", "${latitude.value}")
           // Log.d("Longitude: ", "${longitude.value}")
        }

        return binding.root
    }
}