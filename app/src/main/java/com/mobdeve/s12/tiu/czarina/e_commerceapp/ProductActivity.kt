package com.mobdeve.s12.tiu.czarina.e_commerceapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.mobdeve.s12.tiu.czarina.e_commerceapp.databinding.ActivityProductBinding

class ProductActivity : AppCompatActivity() {
    var binding:ActivityProductBinding? = null

    //these will all be obtained through bundles
    var imgView:Int = 0
    var name:String = ""
    var description:String = ""
    var price:Double = 0.0
    var qty = 1
    var totalprice:Double = 0.0
    var id = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProductBinding.inflate(layoutInflater)
        setContentView(binding!!.root)

        var bundle = intent.extras
        id = bundle!!.getInt("id")
        imgView = bundle!!.getInt("imgView")
        name = bundle!!.getString("name").toString()
        description = bundle!!.getString("description").toString()
        price = bundle!!.getDouble("price")
        qty = bundle!!.getInt("qty")
        totalprice = price

        initialization()
        loadButtons()
        count()


        binding!!.btnBottom.setOnClickListener {
            Log.d("Product Activity.kt", "GOING TO SHOPPING CART ACTIVITY")
            var gotoShoppingCartActivity = Intent(applicationContext, ShoppingCartActivity::class.java)

            gotoShoppingCartActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            var bundle2 = Bundle()

            bundle2.putInt("id", id)
            bundle2.putInt("imgView", imgView)
            bundle2.putString("name", name)
            bundle2.putString("description", description)
            bundle2.putDouble("price", totalprice)
            bundle2.putInt("qty", qty)

            if(binding!!.btnAbove.visibility == View.VISIBLE){
                //means that it is from shopping cart so need update
                bundle2.putString("page", "update")
            }else if(binding!!.btnAbove.visibility == View.GONE){
                //means that it is from home so need add
                bundle2.putString("page", "add")
            }

            gotoShoppingCartActivity.putExtras(bundle2)

            startActivity(gotoShoppingCartActivity)
        }

        binding!!.btnAbove.setOnClickListener { //button above option is only remove
            var gotoShoppingCartActivity = Intent(applicationContext, ShoppingCartActivity::class.java)
            gotoShoppingCartActivity.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
            var bundle3 = Bundle()
            bundle3.putInt("id", id)
            bundle3.putString("remove", "true")
            gotoShoppingCartActivity.putExtras(bundle3)
            startActivity(gotoShoppingCartActivity)
        }
    }

    private fun loadButtons(){
        if(id != -1){//means from shopping cart to product page
            Log.d("PRODUCT ACTIVITY", "ID IS: $id")
            binding!!.btnAbove.visibility = View.VISIBLE
            binding!!.btnBottom.visibility = View.VISIBLE
            binding!!.btnAbove.text = "Remove"
            binding!!.btnBottom.text = "Update Cart - ${totalprice.toString()}"
        }else if(id == -1){ //means from home to product page
            binding!!.btnAbove.visibility = View.GONE
            binding!!.btnBottom.visibility = View.VISIBLE
            binding!!.btnBottom.text = "Add Item - ${totalprice.toString()}"
        }
    }

    private fun initialization(){
        binding!!.imgView.setImageResource(imgView)
        binding!!.tvNamePrice.text = "$name - ${price.toString()}"
        binding!!.qty.text = qty.toString()
    }
    private fun count(){
        binding!!.minus.setOnClickListener {
            if(qty == 1){
                Toast.makeText(applicationContext,"Minimum quantity is 1", Toast.LENGTH_LONG).show()
            }else{
                qty--
                totalprice = compute(qty)
                loadButtons()
                initialization()
            }
        }
        binding!!.plus.setOnClickListener {
            qty++
            totalprice = compute(qty)
            loadButtons()
            initialization()
        }
    }
    private fun compute(qty:Int):Double = qty*price

}