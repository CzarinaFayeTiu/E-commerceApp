package com.mobdeve.s12.tiu.czarina.e_commerceapp

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mobdeve.s12.tiu.czarina.e_commerceapp.databinding.ItemMainRowBinding
import com.mobdeve.s12.tiu.czarina.e_commerceapp.model.Items

class MainAdapter (private val context: Context,
                   private var itemList:ArrayList<Items?>? )
    : RecyclerView.Adapter<MainAdapter.ViewHolder>() {

    private var itemlist = itemList

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainAdapter.ViewHolder {
        val itemBinding = ItemMainRowBinding
            .inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: MainAdapter.ViewHolder, position: Int) {
        holder.bindItem(itemlist!![position]!!, position)
    }

    override fun getItemCount() = itemlist!!.size

    inner class ViewHolder(private val itemBinding: ItemMainRowBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bindItem(item: Items, index:Int) {
            itemBinding.imgView.setImageResource(item.imageView)
            itemBinding.tvName.text = item.name
            itemBinding.tvDescription.text = item.description
            itemBinding.tvPrice.text = item.price.toString()

            var card = itemBinding.cardView

            card.setOnClickListener{
                var gotoProductActivity = Intent(context, ProductActivity::class.java)
                gotoProductActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

                var bundle = Bundle()
                bundle.putInt("id", -1)
                bundle.putInt("imgView", item.imageView)
                bundle.putString("name", item.name)
                bundle.putString("description", item.description)
                bundle.putDouble("price", item.price)
                bundle.putInt("qty", 1)
                gotoProductActivity.putExtras(bundle)

                context.startActivity(gotoProductActivity)
            }

        }

    }

}