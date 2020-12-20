package com.example.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.main_menu.*

class MainMenuFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.main_menu, container, false)

        //Profile fül gomb deklarálása és click listener hozzácsatolása
        val profileBTN = root.findViewById<Button>(R.id.profileButton)

        profileBTN.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_mainMenuFragment_to_profileFragment)
        }

        //Éttermek fül gomb deklarálása és click listener hozzácsatolása
        val restaurantBTN = root.findViewById<Button>(R.id.restaurantsButton)

        restaurantBTN.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_mainMenuFragment_to_splashScreenFragment)
        }

        return root
    }
}

