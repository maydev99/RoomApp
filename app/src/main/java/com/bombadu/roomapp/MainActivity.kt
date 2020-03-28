package com.bombadu.roomapp

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var db : UserDatabase
    private lateinit var listView: ListView
    private lateinit var idList: MutableList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listView = findViewById<ListView>(R.id.list_view)
        idList = mutableListOf()

        floatingActionButton.setOnClickListener {
            startActivity(Intent(this, EntryActivity::class.java))
        }



    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.delete_all) {
            val deleteThread = Thread {
                db.userDao().deleteAll()
            }

            deleteThread.start()
            getMyData()

        }
        return super.onOptionsItemSelected(item)
    }

    override fun onStart() {
        super.onStart()
        getMyData()

    }

    private fun getMyData() {
        db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "UserDB"
        ).build()

        refreshData()

        listView.onItemClickListener = AdapterView.OnItemClickListener { _, _, position, _ ->
            val deleteItemThread = Thread{
                val myId = idList[position]
                db.userDao().deleteByUserId(myId.toInt())
                println("Id: $myId")

            }
            deleteItemThread.start()
            refreshData()
        }
    }

    private fun refreshData(){
        val myList = mutableListOf<String>()

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, myList)
        listView.adapter = adapter

        val thread = Thread {
            db.userDao().getAllUsersWithAgeSort().forEach {
                myList.add("Name: ${it.username} Age: ${it.age}")
                idList.add(it.id.toString())

            }
        }
        thread.start()
    }

}


