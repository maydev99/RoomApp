package com.bombadu.roomapp

import android.os.Bundle
import android.text.TextUtils
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room
import kotlinx.android.synthetic.main.activity_entry.*

class EntryActivity : AppCompatActivity() {

    private lateinit var db: UserDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_entry)

        db = Room.databaseBuilder(
            applicationContext,
            UserDatabase::class.java, "UserDB"
        ).build()

        bSaveButton.setOnClickListener {
            val userEntity = UserEntity()
            if (TextUtils.isEmpty(bNameText.text) || TextUtils.isEmpty(bAgeText.text)){
                Toast.makeText(this, "Field Empty", Toast.LENGTH_SHORT).show()
            } else {
                val myName = bNameText.text.toString()
                val myAge = bAgeText.text.toString()
                userEntity.username = myName
                userEntity.age = myAge
                val saveThread = Thread {
                    db.userDao().saveUser(userEntity)

                }
                saveThread.start()
                finish()
            }



        }


    }
}
