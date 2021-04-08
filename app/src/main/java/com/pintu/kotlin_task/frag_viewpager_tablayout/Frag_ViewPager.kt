package com.pintu.kotlin_task.frag_viewpager_tablayout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.pintu.kotlin_task.R
import kotlinx.android.synthetic.main.activity_frag__view_pager.*

class Frag_ViewPager : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_frag__view_pager)

        viewPager.adapter = PageAdapter(supportFragmentManager)
        indicator.setupWithViewPager(viewPager)

    }
}