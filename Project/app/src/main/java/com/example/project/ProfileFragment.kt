package com.example.project

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.project.localDB.AppDB
import com.example.project.localDB.Profile

class ProfileFragment: Fragment() {
    private lateinit var list: List<Profile>
    private lateinit var imageView: ImageButton
    private  lateinit var userName: TextView
    private  lateinit var address: TextView
    private  lateinit var phoneNumber: TextView
    private  lateinit var email: TextView

    private var profilePicture = "";
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.profile, container, false)

        imageView = root.findViewById(R.id.user_profile_photo)
        userName = root.findViewById(R.id.user_profile_name)
        address = root.findViewById(R.id.address_text)
        phoneNumber = root.findViewById(R.id.phone_number_text)
        email = root.findViewById(R.id.email_text)

        AsyncTask.execute{
            //Kiolvasás a db bol
            list = AppDB.getDatabase(this.requireActivity().baseContext)!!.DAO()!!.getAll as List<Profile>
            profilePicture = list[0].profile_picture
            userName.text = list[0].name
            address.text = "Utca: " + list[0].address
            phoneNumber.text = "Telefon: " + list[0].phone_number
            email.text = "E-mail: " + list[0].email
        }

        Glide.with(imageView)
            .load(profilePicture)
            .override(380, 580)
            .placeholder(R.drawable.profile_circular_border_imageview)
            .into(imageView)

        return root
    }
}