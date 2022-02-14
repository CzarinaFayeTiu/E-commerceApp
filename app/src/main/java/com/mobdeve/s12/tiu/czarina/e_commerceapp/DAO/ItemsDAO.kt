package com.mobdeve.s12.tiu.czarina.e_commerceapp.DAO

import com.mobdeve.s12.tiu.czarina.e_commerceapp.model.Items

interface ItemsDAO {
    fun addItem(item: Items?): Long
    fun getItems():ArrayList<Items?>?
    fun deleteItems(item:Items?):Long
}