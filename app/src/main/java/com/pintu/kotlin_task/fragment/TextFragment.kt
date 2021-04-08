package com.pintu.kotlin_task.fragment

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.pintu.kotlin_task.R

class TextFragment : Fragment() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Get the custom view for this fragment layout
        val view = inflater!!.inflate(R.layout.activity_text_fragment,container,false)

        // Get the text view widget reference from custom layout
        val tv = view.findViewById<TextView>(R.id.text_view)

        // Set a click listener for text view object
        tv.setOnClickListener {

            // Change the text color
            tv.setTextColor(Color.RED)

            // Show click confirmation
            Toast.makeText(view.context,"Text Clicked ",Toast.LENGTH_SHORT).show()
        }
        // Return the fragment view/layout
        return view
    }
}