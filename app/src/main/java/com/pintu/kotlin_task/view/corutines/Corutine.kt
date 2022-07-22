package com.pintu.kotlin_task.view.corutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import coil.api.load

import com.pintu.kotlin_task.R
import kotlinx.android.synthetic.main.activity_corutine.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class Corutine : AppCompatActivity(), CoroutineScope by MainScope() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_corutine)

        btn_get_image.setOnClickListener {
            progress_bar.visibility = View.VISIBLE
            btn_get_image.visibility = View.INVISIBLE
            // Use launch and pass Dispatchers.Main to tell that
            // the result of this Coroutine is expected on the main thread.
            launch(Dispatchers.Main) {
                // Try catch block to handle exceptions when calling the API.
                try {
                    val response = ApiAdapter.apiClient.getRandomDogImage()
                    // Check if response was successful
                    if (response.isSuccessful && response.body() != null) {
                        // Retrieve data.
                        val data = response.body()!!
                        data.message?.let {
                            // Load URL into the ImageView using Coil.
                            iv_dog_image.load(it)
                            progress_bar.visibility = View.INVISIBLE
                            btn_get_image.visibility = View.VISIBLE
                        }

                    } else {
                        // Show API error.
                        // This is when the server responded with an error.
                        progress_bar.visibility = View.INVISIBLE
                        btn_get_image.visibility = View.VISIBLE
                        Toast.makeText(
                            this@Corutine,
                            "Error Occurred: ${response.message()}",
                            Toast.LENGTH_LONG).show()
                    }
                } catch (e: Exception) {
                    // Show API error. This is the error raised by the client.
                    // The API probably wasn't called in this case, so better check before assuming.
                    progress_bar.visibility = View.INVISIBLE
                    btn_get_image.visibility = View.VISIBLE
                    Toast.makeText(this@Corutine,
                        "Error Occurred: ${e.message}",
                        Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}
