package com.mobdeve.s12.tiu.czarina.e_commerceapp.model

class User(var orderID: Int,
           var imageView: Int,
           var name:String,
           var description:String,
           var price:Double,
           var qty:Int){

    constructor():this(0,0,"", "", 0.0, 0)
}