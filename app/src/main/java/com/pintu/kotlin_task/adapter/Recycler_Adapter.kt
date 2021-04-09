package com.pintu.kotlin_task.adapter


import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pintu.kotlin_task.R
import com.pintu.kotlin_task.model.recycler.User

class Recycler_Adapter(private val context: Context,private val list:MutableList<User>) : RecyclerView.Adapter<Recycler_Adapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Recycler_Adapter.MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view :View = inflater.inflate(R.layout.activity_recycler__adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: Recycler_Adapter.MyViewHolder, position: Int) {
        val user = list.get(position)
        holder.name?.text = user.name
        holder.info1?.text = user?.userName + " | " + user?.email
        holder.info2?.text = user?.phone + " | " + user?.website
        val addressObj = user.addressObject
        holder.address?.text = addressObj?.suite + "," + addressObj?.street + "," + addressObj?.city + "," + addressObj?.zipCode
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(var view: View):RecyclerView.ViewHolder(view){

        var name : TextView? =null
        var info1 : TextView? = null
        var info2 : TextView?= null
        var address : TextView?= null

        init {
            name = view.findViewById(R.id.txt_user_name)
            info1 = view.findViewById(R.id.txt_user_info1)
            info2 = view.findViewById(R.id.txt_user_info2)
            address = view.findViewById(R.id.txt_user_address)
        }
    }
}