package com.mobdeve.s12.tiu.czarina.e_commerceapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mobdeve.s12.tiu.czarina.e_commerceapp.databinding.ItemMainRowBinding
import com.mobdeve.s12.tiu.czarina.e_commerceapp.model.User

class UserAdapter(private val context: Context,
                  private var userList: ArrayList<User?>?)
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    private var userlist = userList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserAdapter.ViewHolder {
        val itemBinding = ItemMainRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: UserAdapter.ViewHolder, position: Int) {
        holder.bindUser(userlist!![position]!!, position)
    }

    override fun getItemCount() = userlist!!.size

    fun addUsers(usersArrayList: ArrayList<User?>?) {
        userList!!.clear()
        userList!!.addAll(usersArrayList!!)
        notifyDataSetChanged()
    }
    inner class ViewHolder(private val itemBinding: ItemMainRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bindUser(user:User, index:Int){
            itemBinding.imgView.setImageResource(user.imageView)
            itemBinding.tvName.text = user.name
            itemBinding.tvDescription.text = user.description
            itemBinding.tvPrice.text = user.price.toString()

            var card = itemBinding.cardView

            card.setOnClickListener{
                var gotoProductActivity = Intent(context, ProductActivity::class.java)
                gotoProductActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                var bundle = Bundle()
                bundle.putInt("id", user.orderID)
                bundle.putInt("imgView", user.imageView)
                bundle.putString("name", user.name)
                bundle.putString("description", user.description)
                bundle.putDouble("price", user.price)
                bundle.putInt("qty", user.qty)
                gotoProductActivity.putExtras(bundle)

                context.startActivity(gotoProductActivity)
            }


        }
    }

}