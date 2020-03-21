package com.bombadu.roomapp

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class UserEntity {

    @PrimaryKey (autoGenerate = true)
    var id: Int = 0

    @ColumnInfo (name = "UserName")
    var username: String = ""

    @ColumnInfo (name = "Age")
    var age: String = ""

}
