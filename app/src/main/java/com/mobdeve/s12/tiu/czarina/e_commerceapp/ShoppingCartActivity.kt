package com.mobdeve.s12.tiu.czarina.e_commerceapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.tiu.czarina.e_commerceapp.DAO.UserDAO
import com.mobdeve.s12.tiu.czarina.e_commerceapp.DAO.UserDAODatabase
import com.mobdeve.s12.tiu.czarina.e_commerceapp.databinding.ActivityShoppingCartBinding
import com.mobdeve.s12.tiu.czarina.e_commerceapp.model.User

class ShoppingCartActivity : AppCompatActivity() {
    var binding:ActivityShoppingCartBinding? = null

    var userAdapter: UserAdapter? = null
    var userList: ArrayList<User?> = ArrayList<User?>()
    lateinit var userDAO: UserDAO

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("SHOPPING CART ACTIVITY", "HERE AT SHOPPING CART ACTIVITY ")

        userDAO = UserDAODatabase(applicationContext)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        userList = userDAO.getUsers()!!

        userAdapter = UserAdapter(applicationContext, userList!!)

        binding!!.rvUser.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL, false)

        binding!!.rvUser.adapter = userAdapter



        var bundle = intent.extras
        Log.d("BUNDLE CONTENT: ", "$bundle")
        if(bundle != null){ //came from product activity
            var bundle = intent.extras
            var id = bundle!!.getInt("id")
            var imgView = bundle!!.getInt("imgView")
            var name = bundle!!.getString("name").toString()
            var description = bundle!!.getString("description").toString()
            var price = bundle!!.getDouble("price")
            var qty = bundle!!.getInt("qty")
            var page = bundle!!.getString("page").toString()
            var remove = bundle!!.getString("remove").toString()

            if(remove == "true"){
                val success = userDAO.deleteUser(id)
                if(success > 0){
                    userAdapter!!.addUsers(userDAO.getUsers())
                } else {
                    Toast.makeText(applicationContext, "Item Not Found.", Toast.LENGTH_SHORT).show()
                }
            }else if(page == "add") {
                var user = User(0, imgView, name, description, price, qty)
                userDAO.addUser(user)
                userAdapter!!.addUsers(userDAO.getUsers()!!)
            }else if(page == "update"){
                val user = User()
                user.orderID = id
                user.imageView = imgView
                user.name = name
                user.description = description
                user.price = price
                user.qty = qty

                val status = userDAO.updateUser(user)
                if (status > 0) {
                    userAdapter!!.addUsers(userDAO.getUsers())
                } else {
                    Toast.makeText(applicationContext, "Item Not Found.", Toast.LENGTH_SHORT).show()
                }
            }
        }

    }
}