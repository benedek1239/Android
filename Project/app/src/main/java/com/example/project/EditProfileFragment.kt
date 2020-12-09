package com.example.project

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController

class EditProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.edit_profile, container, false)

        //Vissza gomb deklarálása és listener hozzácsatolása
        val backBTN = root.findViewById<Button>(R.id.back_button_edit)
        backBTN.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
        }

        return root
    }
}