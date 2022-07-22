package com.pintu.kotlin_task.view.sqllite

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.pintu.kotlin_task.R

class SqlRVAdapter(private val context: Context, private val list: List<EmpModelClass>):RecyclerView.Adapter<SqlRVAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SqlRVAdapter.MyViewHolder {
        val inflater = LayoutInflater.from(context)
        val view :View = inflater.inflate(R.layout.sql_adapter,parent,false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        Log.d("Error","recycler ${list.get(position).userId}")
        holder.id?.text = list.get(position).userId.toString()
        holder.name?.text = list.get(position).userName
        holder.email?.text = list.get(position).userEmail
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(var view: View):RecyclerView.ViewHolder(view){

        var id : TextView? =null
        var name : TextView? = null
        var email : TextView?= null

        init {
            id = view.findViewById(R.id.textViewId)
            name = view.findViewById(R.id.textViewName)
            email = view.findViewById(R.id.textViewEmail)
        }
    }
}