package com.pintu.kotlin_task.fragment

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColor
import com.pintu.kotlin_task.R


class Fragment : AppCompatActivity() {
    private lateinit var button1 : Button
    private lateinit var button2 : Button
    private lateinit var backBtn : ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_fragment)

        button1 = findViewById(R.id.button1)
        button2 = findViewById(R.id.button2)
        backBtn = findViewById(R.id.backbtn)


        backBtn.setOnClickListener(View.OnClickListener {
            super@Fragment.onBackPressed()
            finish()
        })

        button1.setOnClickListener {

            // Get the text fragment instance
            val textFragment = TextFragment()

            // Get the support fragment manager instance
            val manager = supportFragmentManager

            // Begin the fragment transition using support fragment manager
            val transaction = manager.beginTransaction()

            // Replace the fragment on container
            transaction.replace(R.id.fragment_container, textFragment)
            transaction.addToBackStack(null)

            // Finishing the transition
            transaction.commit()
        }

        button2.setOnClickListener {
            // Get the text fragment instance
            val imageFragment = ImageFragment()

            // Get the support fragment manager instance
            val manager = supportFragmentManager

            // Begin the fragment transition using support fragment manager
            val transaction = manager.beginTransaction()

            // Replace the fragment on container
            transaction.replace(R.id.fragment_container, imageFragment)
            transaction.addToBackStack(null)

            // Finishing the transition
            transaction.commit()
        }

    }
}