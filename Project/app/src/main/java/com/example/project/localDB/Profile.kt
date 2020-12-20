package com.example.project.localDB

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

//Profil class
@Entity
class Profile(
    @ColumnInfo(name = "name") var name: String,
    @ColumnInfo(name = "profile_picture") var profile_picture: String,
    @ColumnInfo(name = "address") var address: String,
    @ColumnInfo(name = "phone_number") var phone_number: String,
    @ColumnInfo(name = "email") var email: String
    ) {

    @PrimaryKey(autoGenerate = true)
    var uid = 0
    fun getDebugString(): String? {
        return "UID: " + uid + " name " + name  + " profile_picture: " + profile_picture + " address " + address + " phone_number " + phone_number + " email " + email
    }
}