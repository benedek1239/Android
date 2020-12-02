package com.example.project

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.loader.content.AsyncTaskLoader
import com.example.project.localDB.AppDB
import com.example.project.localDB.Profile

class MainActivity : AppCompatActivity() {
    private lateinit var list: List<Profile>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AsyncTask.execute{

            //Betevés a db be
            AppDB.getDatabase(this)!!.DAO()!!.insertAll(
                Profile(
                    "alma", "https://support.hubstaff.com/wp-content/uploads/2019/08/good-pic.png", "Bethlen Gábor 8", "074039576", "benedek.szabolcs98@freemail.hu")
            );

            //Kiolvasás a db ből
            list = AppDB.getDatabase(this)!!.DAO()!!.getAll as List<Profile>
            for(e in list){
                Log.d("teszt-adatok: Név: ", "${e.name}" );
                Log.d("Profil kép: ", "${e.profile_picture}" );
                Log.d("Cím: ", "${e.address}" );
                Log.d("Telefon szám: ", "${e.phone_number}" );
                Log.d("Email: ", "${e.email}" );
            }
        }
    }

}