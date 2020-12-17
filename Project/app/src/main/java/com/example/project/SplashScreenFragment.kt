package com.example.project


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController


class SplashScreenFragment : Fragment() {

    lateinit var RestaurantShower : RestaurantShowerViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.splash_screen, container, false)

        activity?.let {
            RestaurantShower = ViewModelProvider(it).get(RestaurantShowerViewModel::class.java)
        }

        RestaurantShower.getRestaurantsProperties()

        RestaurantShower.restaurantsReady.observe(viewLifecycleOwner, Observer { res ->
            if (res) {
               view?.findNavController()?.navigate(R.id.action_splashScreenFragment_to_restaurantsFragment)
            }

        })


        return root
    }
}