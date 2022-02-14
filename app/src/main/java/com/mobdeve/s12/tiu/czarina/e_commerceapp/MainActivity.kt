package com.mobdeve.s12.tiu.czarina.e_commerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.mobdeve.s12.tiu.czarina.e_commerceapp.DAO.ItemsDAO
import com.mobdeve.s12.tiu.czarina.e_commerceapp.DAO.ItemsDAOArrayList
import com.mobdeve.s12.tiu.czarina.e_commerceapp.databinding.ActivityMainBinding
import com.mobdeve.s12.tiu.czarina.e_commerceapp.model.Items

class MainActivity : AppCompatActivity() {
    var binding:ActivityMainBinding? = null
    var itemList = ArrayList<Items?>() //contains the data
    var itemsDAO: ItemsDAO = ItemsDAOArrayList()
    var mainAdapter: MainAdapter? = null //use to populate data

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        populateItems()

        mainAdapter = MainAdapter(applicationContext, itemList!!)

        binding!!.rvMain.layoutManager = LinearLayoutManager(applicationContext,
            LinearLayoutManager.VERTICAL, false)

        binding!!.rvMain.adapter = mainAdapter

        binding!!.btnCart.setOnClickListener {
            var gotoShoppingCartActivity = Intent(applicationContext, ShoppingCartActivity::class.java)
            gotoShoppingCartActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            startActivity(gotoShoppingCartActivity)
        }
    }

    fun populateItems(){
        itemList = itemsDAO.getItems()!!

    }
}