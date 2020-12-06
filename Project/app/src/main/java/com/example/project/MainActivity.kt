package com.example.project

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.loader.content.AsyncTaskLoader
import com.example.project.localDB.AppDB
import com.example.project.localDB.Profile
import com.google.gson.JsonArray
import org.json.JSONArray
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var list: List<Profile>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = this;

        //kiolvasás a heroku API bol
        API_interface.endpoints.getStats().enqueue(object: Callback<JSONArray> {
            override fun onResponse(
                call: Call<JSONArray>,
                response: Response<JSONArray>
            ) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show()
                }
                Log.d("adatok: ","${response.body()}");

            }

            override fun onFailure(call: Call<JSONArray>, t: Throwable) {
                Toast.makeText(context, "Failed-onFailure", Toast.LENGTH_SHORT).show()
            }
        })



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