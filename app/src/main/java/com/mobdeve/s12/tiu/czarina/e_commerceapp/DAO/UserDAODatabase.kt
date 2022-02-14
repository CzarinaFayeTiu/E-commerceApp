package com.mobdeve.s12.tiu.czarina.e_commerceapp.DAO

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteException
import com.mobdeve.s12.tiu.czarina.e_commerceapp.model.User

class UserDAODatabase:UserDAO {

    private var userdatabase: UserDatabase? = null
    private var database: SQLiteDatabase? = null

    constructor(context: Context?) {
        userdatabase = UserDatabase(context)
    }

    override fun addUser(user: User?): Long {
        val values = ContentValues()

        values.put(UserDatabase.imageView, user!!.imageView)
        values.put(UserDatabase.name, user!!.name)
        values.put(UserDatabase.description, user!!.description)
        values.put(UserDatabase.price, user!!.price)
        values.put(UserDatabase.qty, user!!.qty)

        database = userdatabase!!.writableDatabase

        val id: Long = database!!.insert(
            UserDatabase.TABLEUSERS,
            null,
            values
        )

        if (database != null) {
            userdatabase!!.close()
        }
        return id
    }

    override fun getUsers(): ArrayList<User?>? {

        val result = java.util.ArrayList<User?>()
        val columns = arrayOf(
            UserDatabase.ORDERID,
            UserDatabase.imageView,
            UserDatabase.name,
            UserDatabase.description,
            UserDatabase.price,
            UserDatabase.qty
        )

        database = userdatabase!!.readableDatabase

        val cursor = database!!.query(
            UserDatabase.TABLEUSERS,
            columns,
            null,
            null,
            null,
            null,
            null
        )

        cursor!!.moveToFirst()
        while (!cursor!!.isAfterLast) {
            val temp = User()

            temp.orderID = cursor!!.getInt(0)
            temp.imageView = cursor!!.getInt(1)
            temp.name = cursor!!.getString(2)
            temp.description = cursor!!.getString(3)
            temp.price = cursor!!.getDouble(4)
            temp.qty = cursor!!.getInt(5)

            result.add(temp)
            cursor!!.moveToNext()
        }

        if (cursor != null) {
            cursor.close()
        }

        if (database != null) {
            userdatabase!!.close()
        }
        return result
    }



    override fun getUser(userid: Int): User? {
        var user: User? = null

        val query = "SELECT " + UserDatabase.ORDERID.toString() + ", " +
                UserDatabase.imageView.toString() + ", " +
                UserDatabase.name.toString() + ", " +
                UserDatabase.description.toString() + ", " +
                UserDatabase.price.toString() + ", " +
                UserDatabase.qty.toString() +
                " from " + UserDatabase.TABLEUSERS.toString() +
                " where " + UserDatabase.ORDERID.toString() + " = " + userid

        var cursor: Cursor?

        database = userdatabase!!.readableDatabase
        try {
            cursor = database!!.rawQuery(query, null)
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                user = User()
                user.orderID = cursor.getInt(0)
                user.imageView = cursor!!.getInt(1)
                user.name = cursor!!.getString(2)
                user.description = cursor!!.getString(3)
                user.price = cursor!!.getDouble(4)
                user.qty = cursor!!.getInt(5)
                cursor.moveToNext()
            }
        } catch (e: SQLiteException) {
            return null
        }

        cursor?.close()

        if (database != null) {
            userdatabase!!.close()
        }

        return user
    }

    override fun updateUser(user: User?): Int {
        val values = ContentValues()

        values.put(UserDatabase.ORDERID, user!!.orderID)
        values.put(UserDatabase.imageView, user!!.imageView)
        values.put(UserDatabase.name, user!!.name)
        values.put(UserDatabase.description, user!!.description)
        values.put(UserDatabase.price, user!!.price)
        values.put(UserDatabase.qty, user!!.qty)

        database = userdatabase!!.writableDatabase

        val records = database!!.update(
            UserDatabase.TABLEUSERS,
            values,
            UserDatabase.ORDERID.toString() + " = " + user!!.orderID,
            null
        )

        if (database != null) {
            userdatabase!!.close()
        }

        return records
    }

    override fun deleteUser(userid: Int): Int {
        val values = ContentValues()
        values.put(UserDatabase.ORDERID, userid)

        database = userdatabase!!.writableDatabase

        val success = database!!.delete(
            UserDatabase.TABLEUSERS,
            UserDatabase.ORDERID.toString() + " = " + userid,
            null)

        if (database != null) {
            userdatabase!!.close()
        }

        return success

    }

}