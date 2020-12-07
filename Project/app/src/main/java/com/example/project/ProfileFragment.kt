package com.example.project

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.project.localDB.AppDB
import com.example.project.localDB.Profile

class ProfileFragment: Fragment() {
    private lateinit var list: List<Profile>
    private lateinit var imageView: ImageButton
    private var profilePicture = "";
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val root = inflater.inflate(R.layout.profile, container, false)
        imageView = root.findViewById(R.id.user_profile_photo)

        AsyncTask.execute{
            //Kiolvasás a db bol
            list = AppDB.getDatabase(this.requireActivity().baseContext)!!.DAO()!!.getAll as List<Profile>
            profilePicture = list[0].profile_picture

        }

        Glide.with(imageView)
            .load(profilePicture)
            .override(380, 580)
            .placeholder(R.drawable.profile_circular_border_imageview)
            .into(imageView)

        return root
    }
}