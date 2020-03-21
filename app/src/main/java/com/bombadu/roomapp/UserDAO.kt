package com.bombadu.roomapp

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDAO {
    @Insert
    fun  saveUser(user: UserEntity)

    @Query (value = "Select * from UserEntity")
    fun getAllUsers() : List<UserEntity>

    @Query (value = "Select * from UserEntity order by username")
    fun getAllUsersWithNameSort() : List<UserEntity>

    @Query (value = "Select * from UserEntity order by age")
    fun getAllUsersWithAgeSort() : List<UserEntity>


}
