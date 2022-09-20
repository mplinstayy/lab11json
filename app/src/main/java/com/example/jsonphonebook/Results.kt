package com.example.jsonphonebook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class Results : AppCompatActivity() {

    private val phoneBook: MutableList<PhoneBook> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results)
        getPhoneBook()
        phoneBook.forEach {
            Log.d("LAB11", it.toString())
        }
    }

    private fun getPhoneBook() {
        val preferences = getSharedPreferences("prefs", MODE_PRIVATE)
        if (preferences.contains("str")){
            val str = preferences.getString("str", "")
            phoneBook.addAll(Gson().fromJson(str, object : TypeToken<MutableList<PhoneBook>>(){}.type))
        }
    }

    //то же самое что и getPhoneBook
    private fun getContacts(){
        val pref= getSharedPreferences("prefs", MODE_PRIVATE)
        var json:String=""
        if (!pref.contains("str")){
            return
        } else {
            json = pref.getString("str","").toString()
        }
        val tempList = Gson().fromJson<List<PhoneBook>>(json, object: TypeToken<List<PhoneBook>>(){}.type)

    }
}