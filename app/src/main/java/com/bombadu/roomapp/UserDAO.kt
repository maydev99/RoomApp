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

    @Query("DELETE FROM UserEntity")
    fun deleteAll()

    @Query("DELETE FROM UserEntity WHERE id = :id")
    fun deleteByUserId(id: Int)






}
