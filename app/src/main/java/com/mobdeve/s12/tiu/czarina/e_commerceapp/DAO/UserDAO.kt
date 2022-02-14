package com.mobdeve.s12.tiu.czarina.e_commerceapp.DAO

import com.mobdeve.s12.tiu.czarina.e_commerceapp.model.User
import java.util.ArrayList

interface UserDAO {
    fun addUser(user: User?): Long
    fun getUsers(): ArrayList<User?>?
    fun getUser(userid: Int): User?
    fun updateUser(user: User?): Int
    fun deleteUser(userid: Int): Int
}