package com.pintu.kotlin_task.frag_viewpager_tablayout

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class PageAdapter(fm:FragmentManager): FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        when(position){
            0 -> (return Fragment1())
            1 -> (return Fragment2())
            2 -> (return Fragment3())
            else -> (return Fragment1())
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position){
            0 -> (return "Messages")
            1 ->  (return "Status")
            2 -> (return "Call")
        }
        return super.getPageTitle(position)
    }

    override fun getCount(): Int {
        return 3
    }


}