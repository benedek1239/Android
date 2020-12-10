package com.example.project

import android.os.AsyncTask
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.project.localDB.AppDB
import com.example.project.localDB.Profile

class EditProfileFragment : Fragment() {
    //változók deklarálása
    private lateinit var list: List<Profile>
    private  lateinit var userName: EditText
    private  lateinit var address: EditText
    private  lateinit var phoneNumber: EditText
    private  lateinit var email: EditText

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.edit_profile, container, false)

        //Változókhoz view csatolás
        userName = root.findViewById(R.id.user_name_edit)
        address = root.findViewById(R.id.address_edit)
        phoneNumber = root.findViewById(R.id.phone_number_edit)
        email = root.findViewById(R.id.email_edit)

        AsyncTask.execute{
            //Kiolvasás a db bol
            list = AppDB.getDatabase(this.requireActivity().baseContext)!!.DAO()!!.getAll as List<Profile>

            //változó, hogy tároljuk az utolsó indexet
            val index = list.lastIndex

            //Elemekhez stringek hozzácsatolása
            userName.setText(list[index].name)
            address.setText(list[index].address)
            phoneNumber.setText(list[index].phone_number)
            email.setText(list[index].email)
        }

        //Vissza gomb deklarálása és listener hozzácsatolása
        val backBTN = root.findViewById<Button>(R.id.back_button_edit)
        backBTN.setOnClickListener { view : View ->
            view.findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
        }

        //mentés gomb deklarálása és listener hozzácsatolása
        val saveBTN = root.findViewById<Button>(R.id.save_button_edit)
        saveBTN.setOnClickListener { view : View ->
            AsyncTask.execute{
                //régi adatok törlése
                list = AppDB.getDatabase(this.requireActivity().baseContext)!!.DAO()!!.getAll as List<Profile>
                AppDB.getDatabase(this.requireActivity().baseContext)!!.DAO()!!.deleteById(list.lastIndex)

                //új adatok bevitele
                AppDB.getDatabase(this.requireActivity().baseContext)!!.DAO()!!.insertAll(Profile(userName.text.toString(), "https://support.hubstaff.com/wp-content/uploads/2019/08/good-pic.png", address.text.toString(), phoneNumber.text.toString(), email.text.toString()))
            }
            //vissza navigálás a profil fülbe
            view.findNavController().navigate(R.id.action_editProfileFragment_to_profileFragment)
        }

        return root
    }
}