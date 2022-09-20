package com.example.jsonphonebook

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.core.content.edit
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class AddContacts : AppCompatActivity() {

    private lateinit var editName:EditText
    private lateinit var editNumber:EditText
    private lateinit var btnSaveContact:Button
    private val phoneBook: MutableList<PhoneBook> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_contacts)
        getPhoneBook()
        btnSaveContact = findViewById(R.id.buttonAdd)
        editName = findViewById(R.id.editTextTextPersonName)
        editNumber = findViewById(R.id.editTextNumber)

        val preferences = getSharedPreferences("prefs", Context.MODE_PRIVATE)

        btnSaveContact.setOnClickListener {
            phoneBook.add(PhoneBook(editName.text.toString(), editNumber.text.toString().toInt()))
            preferences.edit {
                this.putString("str", Gson().toJson(phoneBook))
            }
        }
    }

    private fun getPhoneBook() {
        val preferences = getSharedPreferences("prefs", MODE_PRIVATE)
        if (preferences.contains("str")){
            val str = preferences.getString("str", "")
            phoneBook.addAll(Gson().fromJson(str, object : TypeToken<MutableList<PhoneBook>>(){}.type))
        }
    }
}