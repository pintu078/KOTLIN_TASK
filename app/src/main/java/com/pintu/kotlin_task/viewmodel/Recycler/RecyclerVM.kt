package com.pintu.kotlin_task.viewmodel.Recycler

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pintu.kotlin_task.model.recycler.User
import com.pintu.kotlin_task.network.RetroInstance
import com.pintu.kotlin_task.network.RetroService
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Response

class RecyclerVM (application: Application) : AndroidViewModel(application) {

    lateinit var ListData: MutableLiveData<MutableList<User>>

    init {
        ListData = MutableLiveData<MutableList<User>>()
    }

    fun getRecyclerListObserver(): MutableLiveData<MutableList<User>> {
        return ListData
    }


    fun makeApiCall() {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUsers()
        call.enqueue(object : retrofit2.Callback<MutableList<User>> {
            override fun onResponse(call: Call<MutableList<User>>, response: Response<MutableList<User>>) {
                if (response.isSuccessful) {
                    ListData.postValue(response.body())

                    Log.d("saurabh", "Success  ")

                  // Toast.makeText(context, response.body()?.get(0).userName, Toast.LENGTH_SHORT).show()
                } else {
                    try {
                        val jObjError = JSONObject(response.errorBody()!!.string())
                        println("-----DM----------------------------------------")
                        Toast.makeText(
                            getApplication(),
                            jObjError.getString("user_msg"),
                            Toast.LENGTH_SHORT
                        ).show()
                        Log.d("saurabh", "Failure  ")
                        Log.d("saurabh", "Failure  ${jObjError.getString("user_msg")}")
                        println("Failure  ${jObjError.getString("user_msg")}")

                    } catch (e: Exception) {
                        Log.d("saurabh", "${e.message}  ")
                        Toast.makeText(getApplication(), e.message, Toast.LENGTH_SHORT).show()
                    }
                }
            }
            override fun onFailure(call: Call<MutableList<User>>, t: Throwable) {
                Toast.makeText(getApplication(), "Check Internet Connection", Toast.LENGTH_SHORT).show()
                Log.d("saurabh", "Failure  ")
                Log.d("saurabh", "${t.message}")
            }
        })
    }
}