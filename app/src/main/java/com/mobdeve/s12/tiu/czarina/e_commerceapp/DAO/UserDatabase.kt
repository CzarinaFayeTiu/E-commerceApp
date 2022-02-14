package com.mobdeve.s12.tiu.czarina.e_commerceapp.DAO

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class UserDatabase(context: Context?) :
    SQLiteOpenHelper(context, DATABASENAME, null, DATABASEVERSION) {

    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL(CREATE_USER_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLEUSERS)
        onCreate(db)
    }

    companion object {
        private const val DATABASENAME = "user.db"
        private const val DATABASEVERSION = 1

        //column names
        const val TABLEUSERS = "users"
        const val ORDERID = "id"
        const val imageView = "imageView"
        const val name = "name"
        const val description = "description"
        const val price = "price"
        const val qty = "qty"

        private const val CREATE_USER_TABLE = ("create table " + TABLEUSERS + " ( "
                + ORDERID + " integer primary key autoincrement, "
                + imageView + " text, "
                + name + " text, "
                + description + " text, "
                + price + " text, "
                + qty + " text ); ")
    }
}