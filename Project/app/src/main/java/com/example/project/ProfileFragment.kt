package com.example.project

import android.os.AsyncTask
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.bumptech.glide.Glide
import com.example.project.localDB.AppDB
import com.example.project.localDB.Profile


class ProfileFragment: Fragment() {
    //Változók deklarálása
    private lateinit var list: List<Profile>
    private lateinit var imageView: ImageButton
    private  lateinit var userName: TextView
    private  lateinit var address: TextView
    private  lateinit var phoneNumber: TextView
    private  lateinit var email: TextView
    private var profilePicture = "";


    //Létrehozás után meghívódik
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.profile, container, false)

        //Változókhoz view csatolás
        imageView = root.findViewById(R.id.user_profile_photo)
        userName = root.findViewById(R.id.user_profile_name)
        address = root.findViewById(R.id.address_text)
        phoneNumber = root.findViewById(R.id.phone_number_text)
        email = root.findViewById(R.id.email_text)

        AsyncTask.execute{
            //Kiolvasás a db bol
            list = AppDB.getDatabase(this.requireActivity().baseContext)!!.DAO()!!.getAll as List<Profile>

            //változó, hogy tároljuk az utolsó indexet
            val index = list.lastIndex

            //Elemekhez stringek hozzácsatolása
            profilePicture = list[index].profile_picture
            userName.text = list[index].name
            address.text = "Cím: " + list[index].address
            phoneNumber.text = "Telefon: " + list[index].phone_number
            email.text = "E-mail: " + list[index].email
        }


        //Vissza gomb deklarálása és listener hozzácsatolása
        val backBTN = root.findViewById<Button>(R.id.backButtonProfile)
        backBTN.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_profileFragment_to_mainMenuFragment)
        }

        //Szerkesztés gomb deklarálása és listener hozzácsatolása
        val editBTN = root.findViewById<Button>(R.id.editButtonProfile)
        editBTN.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_profileFragment_to_editProfileFragment)
        }

        return root
    }

    override fun onResume() {
        super.onResume()
        //Profilkép betöltése Glide segítségével
        Glide.with(imageView)
                .load(profilePicture)
                .override(380, 580)
                .placeholder(R.drawable.profile_circular_border_imageview)
                .into(imageView)
    }
}