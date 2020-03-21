package com.bombadu.roomapp

import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var db: UserDatabase
    private lateinit var adapter: ArrayAdapter<String>
    private var isSaving = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "UserDB"
        ).build()

        getUsers()

        saveButton.setOnClickListener {
            isSaving = true
            getUsers()

        }


    }

    private fun getUsers() {
        val myList = mutableListOf<String>()
        val userEntity = UserEntity()
        val thread = Thread() {
            if (isSaving) {
                val myName = nameEditText.text.toString()
                val myAge = ageEditText.text.toString()
                userEntity.username = myName
                userEntity.age = myAge
                nameEditText.text = null
                ageEditText.text = null
                db.userDao().saveUser(userEntity)
                adapter.notifyDataSetChanged()
            }

            db.userDao().getAllUsersWithAgeSort().forEach() {
                myList.add("Name: ${it.username} Age: ${it.age}")
                Log.i("Fetch Records", "Name: : ${it.username}")
            }

        }

        thread.start()

        val listView = findViewById<ListView>(R.id.list_view)
        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myList)
        listView.adapter = adapter
        adapter.notifyDataSetChanged()
    }
}
