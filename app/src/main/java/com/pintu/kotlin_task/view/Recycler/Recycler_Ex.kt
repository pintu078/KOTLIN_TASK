package com.pintu.kotlin_task.view.Recycler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pintu.kotlin_task.R
import com.pintu.kotlin_task.adapter.Recycler_Adapter
import com.pintu.kotlin_task.model.recycler.User
import com.pintu.kotlin_task.viewmodel.Recycler.RecyclerVM

class Recycler_Ex : AppCompatActivity() {

    private lateinit var backBtn : ImageButton
    private lateinit var progressbar : ProgressBar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recycler__ex)
        progressbar = findViewById(R.id.progress_bar)
        backBtn = findViewById(R.id.backbtn)
        backBtn.setOnClickListener(View.OnClickListener {
            super@Recycler_Ex.onBackPressed()
            finish()
        })

        val recyclerView = findViewById(R.id.recycler_main)as RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)

        val viewModel = ViewModelProviders.of(this).get(RecyclerVM::class.java)
        viewModel.getRecyclerListObserver().observe(this, Observer<List<User>> {

            if (it != null) {

              //  Log.d("saurabh", "${it.userName}")
                val adapter = Recycler_Adapter(this,it.toMutableList())
                recyclerView.adapter =adapter
                Log.d("saurabh", "Successr")
                Log.d("saurabh", "${it.get(0).userName}")

            } else {
                Log.d("saurabh", "FAI-Lurre")
            }

        })
        viewModel.makeApiCall()
    }
}