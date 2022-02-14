package com.mobdeve.s12.tiu.czarina.e_commerceapp.DAO


import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.mobdeve.s12.tiu.czarina.e_commerceapp.R
import com.mobdeve.s12.tiu.czarina.e_commerceapp.model.Items

class ItemsDAOArrayList:ItemsDAO {

    private var itemList = ArrayList<Items?>()

    private var allItems: ArrayList<ArrayList<Any>> = arrayListOf(
        arrayListOf(R.drawable.camera, "Camera", "Device for recording an image", 1000.00),
        arrayListOf(R.drawable.headphones, "Headphones", "worn on or around the head", 2000.00),
        arrayListOf(R.drawable.laptop, "Laptop", "small, portable personal computer (PC)", 3000.00),
        arrayListOf(R.drawable.mouse, "Mouse", "small handheld input device", 4000.00),
        arrayListOf(R.drawable.phone, "Phone", "make and receive calls", 5000.00),
        arrayListOf(R.drawable.powerbank, "Powerbank", "portable charger designed to recharge",6000.00),
        arrayListOf(R.drawable.speaker, "Speaker", "An amplifier and loudspeaker with Bluetooth", 7000.00),
        arrayListOf(R.drawable.tablet, "Tablet", "portable personal computer with a touchscreen", 8000.00),
        arrayListOf(R.drawable.tv, "Television", "electronic delivery of moving images", 9000.00),
        arrayListOf(R.drawable.keyboard, "Keyboard", "used to enter characters and functions", 10000.00),
    )


    init {
        for (i in 0..9) {
            itemList.add(Items(allItems[i][0] as Int,
                allItems[i][1] as String,
                allItems[i][2] as String,
                allItems[i][3] as Double
            ))
        }
    }

    override fun addItem(item: Items?): Long {
        itemList.add(item!!)
        return 1L
    }

    override fun getItems(): ArrayList<Items?>? = itemList

    override fun deleteItems(item: Items?): Long {
        itemList.remove(item)
        return 1L
    }

}